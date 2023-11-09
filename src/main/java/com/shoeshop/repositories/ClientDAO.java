package com.shoeshop.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.shoeshop.models.UserOne;

public class ClientDAO {
	private SessionFactory factory = null;
	private Session session = null;
	
	public ClientDAO() {
		factory = new MySessionFactory().getSessionFactory();
		session = factory.openSession();
	}
	
	public List<UserOne> getAllClients() {
		List<UserOne> objs = new ArrayList<>();
		String query = "select * from USERONE";
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			objs = session.createNativeQuery(query, UserOne.class).list();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return objs;
	}
	
	public Map<Integer, Integer> getNumberOfOrder() {
		Map<Integer, Integer> map = new HashMap<>();
		String query = "select u.id as user_id, count(u.id) as num  from USERONE u\r\n"
				+ "join OrderBuy ob on ob.user_id = u.id\r\n"
				+ "group by u.id";
		Transaction tr = session.getTransaction();
	    tr.begin();
		try {
			List<Object[]> objs = session.createNativeQuery(query).list();
			objs.forEach(item -> {
				map.put((int)item[0], (int)item[1]);
			});
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return map;
	}
	
	public boolean update (UserOne user) {
		Transaction tr = session.getTransaction();
		tr.begin();
		try {
			session.update(user);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	public boolean delete (UserOne user) {
		Transaction tr = session.getTransaction();
		tr.begin();
		try {
			session.delete(user);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
}
