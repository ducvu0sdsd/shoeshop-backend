package com.shoeshop.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.shoeshop.models.Brand;
import com.shoeshop.models.FeedBack;
import com.shoeshop.models.Image;
import com.shoeshop.models.Product;
import com.shoeshop.models.ProductDTO;
import com.shoeshop.models.Supplier;

public class ProductDAO {
	private Session session = null;
	private int i;
	private List<String> images = new ArrayList<>();
	private List<String> colors = new ArrayList<>();
	private List<Integer> sizes = new ArrayList<>();
	private List<Double> prices = new ArrayList<>();
	private Product p;
	private int z;
	
	public ProductDAO() {
		session = new MySessionFactory().getSession();
	}
	
	public Product insert (Product product) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			Product product1 = (Product)session.merge(product);
			tr.commit();
			return product1;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	public boolean update (Product product) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			session.update(product);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(Product product) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			session.delete(product);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false;
	}
	
	public Map<Integer, ?> getPriceAVGAndColors() {
		Transaction tr = session.getTransaction();
	    tr.begin();
	    Map<Integer, Double> map = new HashMap<>();
	    try {
	    	String query = "select p.id as product_id, ROUND(AVG(c.retailPrice), 2) as avg from Product p\r\n"
	    			+ "join Brand b on b.id = p.brand_id\r\n"
	    			+ "join ColorSize c on c.product_id = p.id\r\n"
	    			+ "group by p.id";
	    	List<Object[]> objs = session.createNativeQuery(query).list();
	    	objs.forEach(item -> {
	    		map.put((Integer) item[0], (Double) item[1]);
	    	});
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    }
	    return map;
	}
	
	public Map<Integer, Map<String, List<String>>> getColorAndSizeAndPrice() {
		Map<Integer, Map<String, List<String>>> map = new HashMap<>();
		Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	    	String query = "select p.id as product_id, c.color, c.size, c.quantity, c.retailPrice\r\n"
	    			+ "from Product p join ColorSize c on c.product_id = p.id";
	    	List<Object[]> objs = session.createNativeQuery(query).list();
	    	objs.forEach(item -> {
	    		if (map.get((Integer) item[0]) != null) {
	    			map.get((Integer) item[0]).get("colors").add(item[1].toString());
	    			map.get((Integer) item[0]).get("sizes").add(item[2].toString());
	    			map.get((Integer) item[0]).get("quantity").add(item[3].toString());
	    			map.get((Integer) item[0]).get("prices").add(item[4].toString());
	    		} else {
	    			Map<String, List<String>> map1 = new HashMap<>();
	    			List<String> l1 = new ArrayList<>();
		    		l1.add(item[1].toString());
		    		List<String> l2 = new ArrayList<>();
		    		l2.add(item[2].toString());
		    		List<String> l3 = new ArrayList<>();
		    		l3.add(item[3].toString());
		    		List<String> l4 = new ArrayList<>();
		    		l4.add(item[4].toString());
		    		map1.put("colors", l1);
		    		map1.put("sizes", l2);
		    		map1.put("quantity", l3);
		    		map1.put("prices", l4);
		    		map.put((Integer) item[0], map1);
	    		}
	    	});
	    } catch (Exception e) {
	    	tr.rollback();
	        e.printStackTrace();
		}
		return map;
	}
	
	public List<ProductDTO> getAllProduct () {
		List<ProductDTO> l = new ArrayList<>();
		Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	    	List<Object[]> objs = session.createNativeQuery("select p.id, p.category, p.name, p.overview, p.brand_id, i.id AS imageid, i.image, b.brandName, b.logo, s.id as supplier_id, s.supplierName, s.supplierAddress, s.supplierPhone from Product p\r\n"
	    			+ "join Image i on p.id = i.product_id\r\n"
	    			+ "join Brand b on p.brand_id = b.id\r\n"
	    			+ "join Supplier s on p.supplier_id = s.id").list();
	    	i = 0;
	    	z = 0;
	    	AtomicInteger auto = new AtomicInteger();
	    	
	    	objs.forEach(obj -> {
	    		int indexForEach = auto.getAndIncrement();
    			int index = Integer.parseInt(obj[0].toString());
    			if (z == 0) {
        			String image1 = (String)obj[6];
        			if (!images.contains(image1)) images.add(image1);
    			}
	    		if (index == i) {
	    			String image1 = (String)obj[6];
        			if (!images.contains(image1)) images.add(image1);
	    		} else {
    				if (z == 1) {
    					// handle the last ProductDTO
	    				List<Image> l_images = new ArrayList<>();
	    				images.forEach(image -> {l_images.add(new Image(image, new Product(i)));});
	    				ProductDTO p1 = new ProductDTO(p,l_images);
	    				l.add(p1);
	    				images.removeAll(images);
	    				String image1 = (String)obj[6];
	        			if (!images.contains(image1)) images.add(image1);
    				}
    				
    				// Clean and Continue
    				i = index;
	    			p = new Product(i,(String)obj[2],(String)obj[3],(String)obj[1],new Brand(Integer.parseInt(obj[5].toString()),(String) obj[7], (String)obj[8]), new Supplier(Integer.parseInt(obj[9].toString()), obj[10].toString(), obj[11].toString(), obj[12].toString()));
	    			z = 1;
	    		}
	    		if (indexForEach == objs.size()- 1) {
	    			// handle the last ProductDTO
    				List<Image> l_images = new ArrayList<>();
    				images.forEach(image -> {l_images.add(new Image(image, new Product(i)));});
    				ProductDTO p1 = new ProductDTO(p, l_images);                            ;
    				l.add(p1);
    				images.removeAll(images);
	    		}
	    	});
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    }
		return l;
	}
	
	public List<Map<String, Object>> getAllFeedbackByEachProduct() {
		List<Map<String, Object>> l = new ArrayList<>();
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			String query = "select p.id, f.id as feedback_id, f.content, f.user_id, f.datetime   from Product p\r\n"
					+ "join FeedBack f on f.product_id = p.id";
			List<Object[]> objs = session.createNativeQuery(query).list();
			
			List<Integer> l_product = new ArrayList<>();
			objs.forEach(obj -> {
				if (!l_product.contains((int) obj[0])) {
					l_product.add((int)obj[0]);
				}
			});

			l_product.forEach(item -> {
				List<Map<String, Object>> l_f = new ArrayList<>();
				Map<String, Object> map_big = new HashMap<>();
				objs.forEach(obj -> {
					if ((int)obj[0] == item) {
						Map<String, Object> m = new HashMap<>();
					
						m.put("feedback_id", (int) obj[1]);
						m.put("content", (String) obj[2]);
						m.put("user_id", (int) obj[3]);
						m.put("datetime", (Date) obj[4]);
						l_f.add(m);
					}
				});
				map_big.put("product_id", item);
				map_big.put("list_feedback", l_f);
				l.add(map_big);
			});
			System.out.println(l);
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return l;
	}
	
	public boolean insertFeedBack(FeedBack fb) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			session.persist(fb);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
}
