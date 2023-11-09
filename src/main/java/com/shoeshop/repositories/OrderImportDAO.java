package com.shoeshop.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;

import com.shoeshop.models.Brand;
import com.shoeshop.models.OrderImport;

public class OrderImportDAO{
	private SessionFactory factory = null;
	private Session session = null;
	
	public OrderImportDAO() {
		factory = new MySessionFactory().getSessionFactory();
		session = factory.openSession();
	}
	
	public OrderImport insert (OrderImport orderImport) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			OrderImport o = (OrderImport) session.merge(orderImport);
			tr.commit();
			return o;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	public List<Map<String, Object>> getAllOrderImport() {
		List<Map<String, Object>> l = new ArrayList<>();
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			String query = "select  ot.id as order_id,\r\n"
					+ "		ot.date as order_date,\r\n"
					+ "		ot.note as order_note,\r\n"
					+ "		oi.id as order_item_id,\r\n"
					+ "		oi.quantity as order_item_quantity,\r\n"
					+ "		cs.size as order_item_size,\r\n"
					+ "		cs.color as order_item_color,\r\n"
					+ "		cs.importPrice as order_item_import_price,\r\n"
					+ "		cs.retailPrice as order_item_retail_price,\r\n"
					+ "		p.id as product_id,\r\n"
					+ "		p.name as product_name,\r\n"
					+ "		p.category as product_category,\r\n"
					+ "		b.brandName as product_brand,\r\n"
					+ "		s.id as supplier_id,\r\n"
					+ "		s.supplierName as supplier_name\r\n"
					+ "from OrderImport ot\r\n"
					+ "join Order_Item oi on oi.orderImport_id = ot.id\r\n"
					+ "join ColorSize cs on cs.id = oi.colorSize_id\r\n"
					+ "join Product p on p.id = cs.product_id\r\n"
					+ "join Brand b on b.id = p.brand_id\r\n"
					+ "join Supplier s on s.id = p.supplier_id";
			List<Object[]> objs = session.createNativeQuery(query).list();
			List<Integer> list_order_id = new ArrayList<>();
			Map<String, Object> map_list_order_item = new HashMap<>();
			objs.forEach(o -> {
				Map<String, Object> map_order_import = new HashMap<>();
				if (!list_order_id.contains((int) o[0])) {
					list_order_id.add((int) o[0]);
					map_order_import.put("order_id", (int) o[0]);
					map_order_import.put("order_date", (Date) o[1]);
					map_order_import.put("order_note", (String) o[2]);
					l.add(map_order_import);
				}
			});
			l.forEach(item -> {
				List<Map<String, Object>> order_items = new ArrayList<>();
				objs.forEach(obj -> {
					if ((int) item.get("order_id") == (int) obj[0]) {
						Map<String, Object> map1 = new HashMap<>();
						Map<String, Object> map_product = new HashMap<>();
						Map<String, Object> map_supplier = new HashMap<>();
						Map<String, Object> map_order_item = new HashMap<>();
						
						//order_item
						map_order_item.put("id", (int) obj[3]);
						map_order_item.put("quantity", (int) obj[4]);
						map_order_item.put("size", (int) obj[5]);
						map_order_item.put("color", (String) obj[6]);
						map_order_item.put("import_price", (double) obj[7]);
						map_order_item.put("retail_price",(double) obj[8]);
						map1.put("order_item", map_order_item);
						
						// product
						map_product.put("id", (int) obj[9]);
						map_product.put("name", (String) obj[10]);
						map_product.put("category", (String) obj[11]);
						map_product.put("brand", (String) obj[12]);
						map1.put("product", map_product);
						
						// supplier
						map_supplier.put("id", (int) obj[13]);
						map_supplier.put("name", (String) obj[14]);
						map1.put("supplier", map_supplier);
						
						order_items.add(map1);
					}
				});
				item.put("order_items", order_items);
			});
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return l;
	}
}