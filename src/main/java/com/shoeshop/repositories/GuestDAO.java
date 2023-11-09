package com.shoeshop.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.shoeshop.models.Guest;
import com.shoeshop.models.Order_Item;

public class GuestDAO {
	private SessionFactory factory = null;
	private Session session = null;
	
	public GuestDAO() {
		factory = new MySessionFactory().getSessionFactory();
		session = factory.openSession();
	}
	
	public Guest insert (Guest ot) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			Guest ot1 = (Guest) session.merge(ot);
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
