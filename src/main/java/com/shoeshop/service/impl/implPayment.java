package com.shoeshop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoeshop.models.ColorSize;
import com.shoeshop.models.Guest;
import com.shoeshop.models.OrderBuy;
import com.shoeshop.models.Order_Item;
import com.shoeshop.models.Product;
import com.shoeshop.models.UserOne;
import com.shoeshop.repositories.CartDAO;
import com.shoeshop.repositories.ColorSizeDAO;
import com.shoeshop.repositories.GuestDAO;
import com.shoeshop.repositories.OrderBuyDAO;
import com.shoeshop.repositories.OrderItemDAO;
import com.shoeshop.service.PaymentService;

@Service
public class implPayment implements PaymentService{

	private boolean c1,c2,c3,c4,c5;
	private OrderBuy ob;
	private Guest guest;
	
	@Override
	public boolean HandleOrderFromClient(Map<String, Object> body) {
		try {
			String note = (String) body.get("note");
			String status = "Waiting for delivery";
			String paymethod = (String) body.get("method");
			Date date = new Date();
			int user_id = (int) body.get("user_id");
			List<Map<String, ?>> colorsizes = (List<Map<String, ?>>)body.get("colorsizes");			
			ob = null;
			c1 = c2 = false;
			ExecutorService executorService = Executors.newFixedThreadPool(20);
			CountDownLatch latch = new CountDownLatch(colorsizes.size());
			List<Map<String, Object>> list_cre = new ArrayList<>();
			colorsizes.forEach(item -> {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							ColorSize cs = new ColorSize((String) item.get("color"), Integer.parseInt(item.get("size").toString()), Integer.parseInt(item.get("quantity").toString()), new Product(Integer.parseInt(item.get("product_id").toString())));
							ColorSize cre = new ColorSizeDAO().updateByColorAndSizeAndProduct_id(cs);
							Map<String, Object> map = new HashMap<>();
							map.put("ColorSize", cre);
							map.put("quantity", Integer.parseInt(item.get("quantityOrder").toString()));
							list_cre.add(map);
						} finally {
							latch.countDown();
						}
					}
				});
			});
			
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					ob = new OrderBuyDAO().insert(new OrderBuy(date, status, note, paymethod, new UserOne(user_id), null));
					c2 = true;
				}
			});
			while (latch.getCount() > 0 || c2 == false) {System.out.println("Inserting Guest And Updating Color Size");}
			CountDownLatch latch1 = new CountDownLatch(list_cre.size());
			list_cre.forEach(item -> {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Order_Item oi = new Order_Item((int)item.get("quantity"), (ColorSize) item.get("ColorSize"), null, ob);
							new OrderItemDAO().insert(oi);
						} finally {
							latch1.countDown();
						}
					}
				});
			});
			while (latch1.getCount() > 0) {System.out.println("Inserting Order Buy");}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean handleOrderFromGuest(Map<String, Object> body) {
		try {
			//guest
			String name = (String) body.get("name");
			String email = (String) body.get("email");
			String phone = (String) body.get("phone");
			String address = (String) body.get("address");
			guest = null;
			ob = null;
			c1 = c2 = c3 = false;
			ExecutorService executorService = Executors.newFixedThreadPool(20);
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					guest = new GuestDAO().insert(new Guest(name, phone, address, email));
					c1 = true;
				}
			});
			while(c1 == false) {System.out.println("Insert Guest");}
			
			// other
			String note = (String) body.get("note");
			String status = "Waiting for delivery";
			String paymethod = (String) body.get("method");
			Date date = new Date();
			List<Map<String, ?>> colorsizes = (List<Map<String, ?>>)body.get("colorsizes");			
			ob = null;
			c1 = c2 = false;
			CountDownLatch latch = new CountDownLatch(colorsizes.size());
			List<Map<String, Object>> list_cre = new ArrayList<>();
			colorsizes.forEach(item -> {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							ColorSize cs = new ColorSize((String) item.get("color"), Integer.parseInt(item.get("size").toString()), Integer.parseInt(item.get("quantity").toString()), new Product(Integer.parseInt(item.get("product_id").toString())));
							ColorSize cre = new ColorSizeDAO().updateByColorAndSizeAndProduct_id(cs);
							Map<String, Object> map = new HashMap<>();
							map.put("ColorSize", cre);
							map.put("quantity", Integer.parseInt(item.get("quantityOrder").toString()));
							list_cre.add(map);
						} finally {
							latch.countDown();
						}
					}
				});
			});
			
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					ob = new OrderBuyDAO().insert(new OrderBuy(date, status, note, paymethod, null, guest));
					c2 = true;
				}
			});
			while (latch.getCount() > 0 || c2 == false) {System.out.println("Inserting Guest And Updating Color Size");}
			CountDownLatch latch1 = new CountDownLatch(list_cre.size());
			list_cre.forEach(item -> {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Order_Item oi = new Order_Item((int)item.get("quantity"), (ColorSize) item.get("ColorSize"), null, ob);
							new OrderItemDAO().insert(oi);
						} finally {
							latch1.countDown();
						}
					}
				});
			});
			while (latch1.getCount() > 0) {System.out.println("Inserting Order Buy");}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean handleOrderFromCartOfClient(Map<String, Object> body) {
		try {
			String note = (String) body.get("note");
			String status = "Waiting for delivery";
			String paymethod = (String) body.get("method");
			Date date = new Date();
			int user_id = (int) body.get("user_id");
			List<Map<String, ?>> colorsizes = (List<Map<String, ?>>)body.get("colorsizes");			
			ob = null;
			c1 = c2 = false;
			ExecutorService executorService = Executors.newFixedThreadPool(20);
			CountDownLatch latch = new CountDownLatch(colorsizes.size());
			List<Map<String, Object>> list_cre = new ArrayList<>();
			colorsizes.forEach(item -> {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							ColorSize cs = new ColorSize((String) item.get("color"), Integer.parseInt(item.get("size").toString()), Integer.parseInt(item.get("quantity").toString()), new Product(Integer.parseInt(item.get("product_id").toString())));
							ColorSize cre = new ColorSizeDAO().updateByColorAndSizeAndProduct_id(cs);
							Map<String, Object> map = new HashMap<>();
							map.put("ColorSize", cre);
							map.put("quantity", Integer.parseInt(item.get("quantityOrder").toString()));
							list_cre.add(map);
						} finally {
							latch.countDown();
						}
					}
				});
			});
			
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					ob = new OrderBuyDAO().insert(new OrderBuy(date, status, note, paymethod, new UserOne(user_id), null));
					c2 = true;
				}
			});
			while (latch.getCount() > 0 || c2 == false) {System.out.println("Inserting OrderBuy And Updating Color Size");}
			c1= false;
			CountDownLatch latch1 = new CountDownLatch(list_cre.size());
			list_cre.forEach(item -> {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Order_Item oi = new Order_Item((int)item.get("quantity"), (ColorSize) item.get("ColorSize"), null, ob);
							new OrderItemDAO().insert(oi);
						} finally {
							latch1.countDown();
						}
					}
				});
			});
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					new CartDAO().deleteAllByUser(user_id);
					c1 = true;
				}
			});
			while (latch1.getCount() > 0 || c1 == false) {System.out.println("Inserting Order Buy");}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
