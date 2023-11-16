package com.shoeshop.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.shoeshop.models.ColorSize;
import com.shoeshop.models.OrderBuy;
import com.shoeshop.models.OrderImport;
import com.shoeshop.models.Order_Item;
import com.shoeshop.models.Product;
import com.shoeshop.repositories.ColorSizeDAO;
import com.shoeshop.repositories.OrderBuyDAO;
import com.shoeshop.repositories.OrderImportDAO;
import com.shoeshop.repositories.OrderItemDAO;
import com.shoeshop.service.OrderService;

@Service
public class implOrder implements OrderService{

	private boolean c1, c2 = false;
	private OrderImport o1;
	private List<Map<String, Object>> l2,l1;

	@Override
	public List<Map<String, Object>> getAllOrderImport() {
		List<Map<String, Object>> l = new OrderImportDAO().getAllOrderImport();
		return l;
	}
	
	@Override
	public boolean insertOrderImport(Map<String, ?> body) throws ParseException {
		try {
			List<?> l = (List<?>) body.get("list_item");
			List<ColorSize> colorSizes = new ArrayList<>();
			List<Order_Item> orderItems = new ArrayList<>();
			CountDownLatch latch = new CountDownLatch(colorSizes.size());
			l.forEach(item -> {
				Map<?, ?> map = (Map<?,?>) item;
				// ColorSize(double retailPrice, double importPrice, String color, int size, Product product)
				ColorSize colorSize = new ColorSize( Double.parseDouble(map.get("txt_retail_price").toString()), Double.parseDouble(map.get("txt_buy_price").toString()), (String)map.get("txt_color"), Integer.parseInt(map.get("txt_size").toString()), Integer.parseInt(map.get("txt_quantity").toString()), new Product(Integer.parseInt(map.get("txt_product_id").toString())));
				colorSizes.add(colorSize);
				
				Order_Item ot = new Order_Item(Integer.parseInt(map.get("txt_quantity").toString()), null, null, null);
				orderItems.add(ot);
			});
			
			ExecutorService executorService = Executors.newFixedThreadPool(100);
			
			// insert OrderImport
			c1 = false;
			String note = (String) body.get("note");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = df.parse((String) body.get("date"));
			OrderImport orderImport = new OrderImport(date, note);
			
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						o1 = new OrderImportDAO().insert(orderImport);
					} finally {
						c1 =true;
					}
				}
			});
			
			// insert Colorsizes
			colorSizes.forEach(item -> {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							ColorSize c = new ColorSizeDAO().getItemByColorAndSize(item.getColor(), item.getSize());
							if (c != null) {
								c.setQuantity(c.getQuantity() + item.getQuantity());
								c.setImportPrice(item.getImportPrice());
								c.setRetailPrice(item.getRetailPrice());
								new ColorSizeDAO().update(c);
								item.setId(c.getId());
							} else {
								ColorSize c12 = new ColorSizeDAO().insert(item);
								item.setId(c12.getId());
							}
						} finally {
							latch.countDown();
						}
					}
				});
			});
			while (latch.getCount() > 0 || c1 == false) {System.out.print("");}
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// insert OrderItem
			for (int i = 0; i< colorSizes.size(); i++) {
				orderItems.get(i).setColorSize(colorSizes.get(i));
				orderItems.get(i).setOrderImport(o1);
			}
			
			CountDownLatch latch1 = new CountDownLatch(orderItems.size());
			orderItems.forEach(item -> {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Order_Item ot = new OrderItemDAO().insert(item);
							System.out.println(ot.getId());
						} finally {
							latch1.countDown();
						}
					}
				});
			});
			while (latch1.getCount() > 0) {System.out.print("");}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> getAllOrderBuy() {
		c1 = c2 = false;
		List<Map<String, Object>> l = new ArrayList<>();
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				l1 = new OrderBuyDAO().getAllOrderBuyWithClient();
				c1 = true;
			}
		});
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				l2 = new OrderBuyDAO().getAllOrderBuyWithGuest();
				c2 = true;
			}
		});
		while (c1 == false || c2 == false){System.out.print("");}
		l1.forEach(item -> {l.add(item);});
		l2.forEach(item -> {l.add(item);});
		return l;
	}

	@Override
	public boolean updateStatusOrder(Map<String, ?> body) {
		int id = Integer.parseInt(body.get("id").toString());
		String status = (String) body.get("status");
		OrderBuy o = new OrderBuy(id, status);
		return new OrderBuyDAO().update(o);
	}

}
