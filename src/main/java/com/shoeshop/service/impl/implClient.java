package com.shoeshop.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.shoeshop.models.UserOne;
import com.shoeshop.repositories.ClientDAO;
import com.shoeshop.service.ClientsService;

@Service
public class implClient implements ClientsService {

	private List<UserOne> users;
	private Map<Integer, Integer> map;
	private boolean c1,c2;
	
	@Override
	public List<Map<String, ?>> getAllClientWithNumberOfOrder() {
		List<Map<String, ?>> l = new ArrayList<>();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		c1 = c2 = false;
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				users = new ClientDAO().getAllClients();
				c1 = true;
			}
		});
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				map = new ClientDAO().getNumberOfOrder();
				c2 = true;
			}
		});
		while(c1 == false || c2 == false) {System.out.print("");}
		users.forEach(user -> {
			Map<String, Object> m = new HashMap<>();
			m.put("client", user);
			if (map.get(user.getId()) != null) {
				m.put("numberOfOrder", map.get(user.getId()));
			} else {
				m.put("numberOfOrder", 0);
			}
			l.add(m);
		});

		return l;
	}

	@Override
	public boolean updateClient(Map<String, ?> body) {
		try {
			Map<String, Object> map = (Map<String, Object>) body.get("user");
			int id = (int) map.get("id");
			String username = (String) map.get("username");
			String password = (String) map.get("password");
			String name = (String) map.get("name");
			String email = (String) map.get("email");
			String phonenumber = (String)map.get("phonenumber");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dateofbirth = df.parse((String)map.get("dateofbirth"));
			String avatar = (String) map.get("avatar");
			boolean admin = (boolean) map.get("admin");
			String address = (String) map.get("address");
			String gender = (String) map.get("gender");
			UserOne user = new UserOne(id, username, password, name, gender, email, phonenumber, dateofbirth, avatar, admin, address);
			return new ClientDAO().update(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteClient(Map<String, ?> body) {
		try {
			Map<String, Object> map = (Map<String, Object>) body.get("user");
			int id = (int) map.get("id");
			UserOne user = new UserOne(id);
			return new ClientDAO().delete(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
