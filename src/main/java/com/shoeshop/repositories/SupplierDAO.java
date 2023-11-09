package com.shoeshop.repositories;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.shoeshop.models.Brand;
import com.shoeshop.models.Supplier;

public class SupplierDAO {
	private SessionFactory factory = null;
	private Session session = null;
	
	public SupplierDAO() {
		factory = new MySessionFactory().getSessionFactory();
		session = factory.openSession();
	}
	public boolean insert (Supplier supplier) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			session.persist(supplier);
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
	public java.util.List<Supplier> getAllSuppliers() {
		java.util.List<Supplier> suppliers = new ArrayList<>();
		Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	    	suppliers = session.createQuery("from Supplier", Supplier.class).list();
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    }
		return suppliers;
	}
	
	public boolean update(Supplier supplier) {
		Transaction tr = session.getTransaction();
		tr.begin();
		try {
			session.update(supplier);
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
	
	public boolean delete(Supplier s) {
	    Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	    	session.delete(s);
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
