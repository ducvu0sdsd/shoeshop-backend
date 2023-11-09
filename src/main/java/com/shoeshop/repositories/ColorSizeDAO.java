package com.shoeshop.repositories;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.shoeshop.models.Brand;
import com.shoeshop.models.ColorSize;

public class ColorSizeDAO {
	private SessionFactory factory = null;
	private Session session = null;
	
	public ColorSizeDAO() {
		factory = new MySessionFactory().getSessionFactory();
		session = factory.openSession();
	}
	
	public ColorSize insert (ColorSize colorSize) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			ColorSize c = (ColorSize) session.merge(colorSize);
			tr.commit();
			return c;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	public boolean update (ColorSize c) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			session.update(c);
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
	
	public ColorSize updateByColorAndSizeAndProduct_id(ColorSize c) {
	    Transaction tr = session.getTransaction();
	    tr.begin();
	    ColorSize updatedColorSize = null;
	    try {
	        String hql = "UPDATE ColorSize SET quantity = :quantity WHERE color = :color and size = :size and product_id = :product_id";
	        Query query = session.createQuery(hql);
	        query.setParameter("quantity", c.getQuantity());
	        query.setParameter("color", c.getColor());
	        query.setParameter("size", c.getSize());
	        query.setParameter("product_id", c.getProduct().getId());
	        int rowCount = query.executeUpdate();
	        tr.commit();

	        if (rowCount > 0) {
	            updatedColorSize = getItemByColorAndSize(c.getColor(), c.getSize());
	        }
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return updatedColorSize;
	}
	
	public ColorSize getItemByColorAndSize (String color, int size) {
		Transaction tr = session.getTransaction();
	    tr.begin();
	    ColorSize c = null;
		try {
			c = session.createQuery("from ColorSize where color= :colorparam and size= :sizeparam", ColorSize.class)
					.setParameter("colorparam", color)
					.setParameter("sizeparam", size)
					.uniqueResult();
			tr.commit();
			return c;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return c;
	}
}
