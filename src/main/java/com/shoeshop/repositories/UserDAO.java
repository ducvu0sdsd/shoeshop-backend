package com.shoeshop.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shoeshop.models.UserOne;



public class UserDAO {
	private SessionFactory factory = null;
	private Session session = null;
	
	public UserDAO() {
		factory = new MySessionFactory().getSessionFactory();
		session = factory.openSession();
	}
	
	public UserOne getUserByUsername(String username) {
	    Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	    	UserOne user = session.createQuery("FROM UserOne WHERE username = :username", UserOne.class)
	                .setParameter("username", username)
	                .uniqueResult();
	        return user;
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public UserOne getUserByUsernameAndPassword(String username, String password, BCryptPasswordEncoder bCryptPasswordEncoder) {
	    Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	    	UserOne user = session.createQuery("FROM UserOne WHERE username = :username", UserOne.class)
	                .setParameter("username", username)
	                .uniqueResult();
	        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
	            tr.commit();
	            return user;
	        } else {
	            tr.rollback();
	            return null;
	        }
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	        return null;
	    }
	}

	
	public boolean insert (UserOne user) {
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			session.persist(user);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(UserOne user) {
		Transaction tr = session.getTransaction();
		tr.begin();
		try {
			session.update(user);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(UserOne user) {
	    Transaction tr = session.getTransaction();
	    tr.begin();
	    try {
	    	session.delete(user);
            tr.commit();
            return true;
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public List<UserOne> getAllUser() {
		List<UserOne> users = new ArrayList<>();
		Transaction tr = session.getTransaction();
		tr.begin();
		try {
			users = session.createQuery("FROM UserOne", UserOne.class).list();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return users;
	}
}
