package com.shoeshop.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.shoeshop.models.Cart;
import com.shoeshop.models.ColorSize;

public class CartDAO {
	private SessionFactory factory = null;
	private Session session = null;
	
	public CartDAO() {
		factory = new MySessionFactory().getSessionFactory();
		session = factory.openSession();
	}
	public boolean insert (Cart cart) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			session.persist(cart);
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
	
	public boolean delete (Cart cart) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			session.delete(cart);
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
	
	public List<Cart> getCartsByUser (int id) {
		Transaction tr = session.getTransaction();
	    tr.begin();
	    List<Cart> carts= new ArrayList<>();
		try {
			carts = session.createQuery("from Cart where user_id = :user_id", Cart.class)
					.setParameter("user_id", id)
					.list();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return carts;
	}
	
	public boolean deleteAllByUser(int id) {
	    Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	        String hql = "DELETE from Cart WHERE user_id = :user_id";
	        Query query = session.createQuery(hql);
	        query.setParameter("user_id", id);
	        int rowCount = query.executeUpdate();
	        tr.commit();
	        return rowCount > 0;
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return false;
	}
	
	public boolean update (Cart cart) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			session.update(cart);
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

	public Cart getCartsByUserAndColorSize (int user_id, int colorsize_id) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			Cart cart = session.createQuery("from Cart WHERE user_id = :userId AND colorSize_id = :colorsizeId", Cart.class)
					.setParameter("userId", user_id)
					.setParameter("colorsizeId", colorsize_id)
					.uniqueResult();
			tr.commit();
			return cart;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	public boolean updateCartByID(int quantity, int id, int co_id) {
	    Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	        String hql = "UPDATE Cart SET quantity = :quantity WHERE user_id = :userId AND colorSize_id = :colorsizeId";
	        Query query = session.createQuery(hql);
	        query.setParameter("quantity", quantity);
	        query.setParameter("userId", id);
	        query.setParameter("colorsizeId", co_id);
	        int rowCount = query.executeUpdate();
	        tr.commit();
	        return rowCount > 0;
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return false;
	}
	
	public boolean deleteCartByID(int user_id, int colorsize_id) {
	    Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	        String hql = "DELETE from Cart WHERE user_id = :userId AND colorSize_id = :colorsizeId";
	        Query query = session.createQuery(hql);
	        query.setParameter("userId", user_id);
			query.setParameter("colorsizeId", colorsize_id);
	        int rowCount = query.executeUpdate();
	        tr.commit();
	        return rowCount > 0;
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return false;
	}
}
