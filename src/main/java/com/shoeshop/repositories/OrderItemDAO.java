package com.shoeshop.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.shoeshop.models.Brand;
import com.shoeshop.models.Order_Item;

public class OrderItemDAO {
	private SessionFactory factory = null;
	private Session session = null;
	
	public OrderItemDAO() {
		factory = new MySessionFactory().getSessionFactory();
		session = factory.openSession();
	}
	
	public Order_Item insert (Order_Item ot) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			Order_Item ot1 = (Order_Item) session.merge(ot);
			tr.commit();
			return ot1;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
