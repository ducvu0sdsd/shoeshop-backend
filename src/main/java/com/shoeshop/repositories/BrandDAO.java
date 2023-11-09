package com.shoeshop.repositories;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.shoeshop.models.Brand;

import antlr.collections.List;

public class BrandDAO {
	private SessionFactory factory = null;
	private Session session = null;
	
	public BrandDAO() {
		factory = new MySessionFactory().getSessionFactory();
		session = factory.openSession();
	}
	
	public boolean insert (Brand brand) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			session.persist(brand);
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
	public boolean update(Brand brand) {
		Transaction tr = session.getTransaction();
		tr.begin();
		try {
			session.update(brand);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	public boolean delete(Brand brand) {
	    Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	    	session.delete(brand);
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
	
	public java.util.List<Brand> getAllBrand() {
		java.util.List<Brand> brands = new ArrayList<>();
		Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	    	brands = session.createQuery("from Brand", Brand.class).list();
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    }
		return brands;
	}
}
