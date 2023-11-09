package com.shoeshop.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.shoeshop.models.Brand;
import com.shoeshop.models.Image;

public abstract class AbtractDAO<T> {
	protected Class<T> clazz;
	protected Session session = null;
	
	public AbtractDAO(Class<T> clazz) {
		session = new MySessionFactory().getSession();
		this.clazz = clazz;
	}
	
	public boolean deleteRowByProductID (Object id, String tableName) {
	    Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	        Query query = session.createQuery("delete from " + tableName + " where product_id = :productId");
	        query.setParameter("productId", id);
	        int rowsAffected = query.executeUpdate();
	        tr.commit();
	        return rowsAffected > 0;
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    } finally {
			session.close();
		}
	    return false;
	}
	
	public boolean insert (T t) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			session.persist(t);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return false;
	}
	
	public List<T> getAllObjectByProduct (String tableName, Class<T> clazz, int id) {
		List<T> l = new ArrayList<>();
		Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	    	l = session.createQuery("from " + tableName + " where product_id = " + id, clazz).list();
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    } finally {
			session.close();
		}
		return l;
	}
	
	
}
