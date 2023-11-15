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

import com.shoeshop.models.Brand;
import com.shoeshop.models.FeedBack;
import com.shoeshop.models.Image;
import com.shoeshop.models.Product;
import com.shoeshop.models.ProductDTO;
import com.shoeshop.models.Supplier;
import com.shoeshop.models.UserOne;
import com.shoeshop.repositories.BrandDAO;
import com.shoeshop.repositories.ImageDAO;
import com.shoeshop.repositories.ProductDAO;
import com.shoeshop.service.ProductService;

@Service
public class implProduct implements ProductService{

	private boolean c1;
	private boolean c2;
	private boolean c3;
	private boolean c4;
	private boolean c5;
	private boolean c6;
	private List<ProductDTO> l1;
	private Map<Integer, ?> map1;
	private Map<Integer, Map<String, List<String>>> map2;

	@Override
	public boolean insertProduct(Map<String, List<String>> body) {
		try {
			// Product 
			int brand_id = Integer.parseInt(body.get("brand").get(0));
			int supplier_id = Integer.parseInt(body.get("supplier").get(0));
			String category = body.get("category").get(0);
			String name = body.get("name").get(0);
			String overview = body.get("overview").get(0);
			Product p = new ProductDAO().insert(new Product(name, overview, category, new Brand(brand_id), new Supplier(supplier_id)));
			CountDownLatch latch = new CountDownLatch(body.get("images").size());
			ExecutorService executorService = Executors.newFixedThreadPool(20);
			// Images
			body.get("images").forEach(image -> {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							new ImageDAO().insert(new Image(image,p));
						} finally {
							latch.countDown();
						}	
					}
				});
			});
			while ( latch.getCount() > 0 ) {System.out.print("");}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateProduct(Map<String, List<String>> body) {
		try {
			// Product 
			int id = Integer.parseInt(body.get("id").get(0));
			int brand_id = Integer.parseInt(body.get("brand").get(0));
			int supplier_id = Integer.parseInt(body.get("supplier").get(0));
			String category = body.get("category").get(0);
			String name = body.get("name").get(0);
			String overview = body.get("overview").get(0);
			Product p = new Product(id, name, overview, category, new Brand(brand_id), new Supplier(supplier_id));
			new ProductDAO().update(p);
			c1 = c2 = c3 = c4 = c5 = c6 = false;
			ExecutorService executorService = Executors.newFixedThreadPool(6);
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					c3 = new ImageDAO().deleteRowByProductID(id, "Image");
				}
			});
			while (c3 == false) {}
			// Images
			CountDownLatch latch = new CountDownLatch(body.get("images").size());
			body.get("images").forEach(image -> {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							new ImageDAO().insert(new Image(image,p));
						} finally {
							latch.countDown();
						}
					}
				});
			});
			while ( latch.getCount() > 0 ) {System.out.print("");}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteProduct(Map<String, String> body) {
		try {
			int id1 = Integer.parseInt(body.get("id"));
			c1 = c2 = c3 = c4 = false;
			ExecutorService executorService = Executors.newFixedThreadPool(3);
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					new ImageDAO().deleteRowByProductID(id1, "Image");
					c2 = true;
				}
			});

			executorService.execute(new Runnable() {
				@Override
				public void run() {
					new ProductDAO().delete(new Product(id1));
					c4 = true;
				}
			});
			while (c2 == false || c4 == false) {System.out.print("");}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Map<String, ?>> getAllProduct() {
		try {
			ExecutorService executorService = Executors.newFixedThreadPool(4);
			c1 = c2 = c3 = false;
			List<Map<String, ?>> l = new ArrayList<>();
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					l1 = new ProductDAO().getAllProduct();
					c1 = true;
				}
			});
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					map1 = new ProductDAO().getPriceAVGAndColors();
					c2 = true;
				}
			});
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					map2 = new ProductDAO().getColorAndSizeAndPrice(); // Map<Integer, Map<String, List<String>>>
					c3 = true;
				}
			});
			while (c1 == false || c2 == false || c3 == false) {System.out.println("");}
			l1.forEach(product -> {
				List<Image> images = product.getImages();
				Map<String, Object> map = new HashMap<>();
				map.put("id", product.getProduct().getId());
				map.put("name",product.getProduct().getName());
				map.put("overview", product.getProduct().getOverview());
				map.put("supplier", product.getProduct().getSupplier());
				map.put("category", product.getProduct().getCategory());
				map.put("brand", product.getProduct().getBrand());
				map.put("images", images);
				map.put("price", map1.get(product.getProduct().getId()));
				if (map2.get(product.getProduct().getId()) != null) {
					map.put("sizes", map2.get(product.getProduct().getId()).get("sizes"));
					map.put("colors", map2.get(product.getProduct().getId()).get("colors"));
					map.put("quantity", map2.get(product.getProduct().getId()).get("quantity"));
					map.put("prices", map2.get(product.getProduct().getId()).get("prices"));
				}else {
					map.put("sizes", null);
					map.put("colors", null);
					map.put("quantity", null);
					map.put("prices", null);
				}
				l.add(map);
			});

			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insertFeedBack(Map<String, ?> body) {
		// content, user_id, product_id, date
		String content = (String) body.get("content");
		int user_id = Integer.parseInt(body.get("user_id").toString());
		int product_id = Integer.parseInt(body.get("product_id").toString());
		Date datetime = new Date();
		FeedBack fb = new FeedBack(content, new UserOne(user_id), new Product(product_id), datetime);
		return new ProductDAO().insertFeedBack(fb);
	}

	@Override
	public List<Map<String, Object>> getAllFeedbackByEachProduct() {
		return new ProductDAO().getAllFeedbackByEachProduct();
	}
}
