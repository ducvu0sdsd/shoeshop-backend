package com.shoeshop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shoeshop.models.Cart;
import com.shoeshop.models.ColorSize;
import com.shoeshop.models.UserOne;
import com.shoeshop.repositories.CartDAO;
import com.shoeshop.repositories.ColorSizeDAO;
import com.shoeshop.service.CartService;

@Service
public class implCart implements CartService{

	@Override
	public boolean insertCart(Map<String, ?> body) {
		int user_id = Integer.parseInt(body.get("user_id").toString());
		int quantity = Integer.parseInt(body.get("quantity").toString());
		int size = Integer.parseInt(body.get("size").toString());
		String color = body.get("color").toString();
		ColorSize cs = new ColorSizeDAO().getItemByColorAndSize(color, size);
		Cart cart = new Cart(new UserOne(user_id), cs, quantity);
		Cart cartResult = new CartDAO().getCartsByUserAndColorSize(user_id, cs.getId());
//		int i = 0;
//		while( i == 0) {
//			System.out.println(cs.getId() + " " + user_id);
//		}
		if (cartResult == null) {
			return new CartDAO().insert(cart);
		} else {
			cartResult.setQuantity(cartResult.getQuantity() + cart.getQuantity());
			return new CartDAO().update(cartResult);
		}
	}

	@Override
	public List<Cart> getAllCartByUser(Map<String, ?> body) {
		int user_id = Integer.parseInt(body.get("user_id").toString());
		return new CartDAO().getCartsByUser(user_id);
	}

	@Override
	public boolean updateQuantityByID(Map<String, ?> body) {
		int id = Integer.parseInt(body.get("user_id").toString());
		int colorsize_id = Integer.parseInt(body.get("colorsize_id").toString());
		int quantity = Integer.parseInt(body.get("quantity").toString());
		return new CartDAO().updateCartByID(quantity, id, colorsize_id);
	}

	@Override
	public boolean deleteCart(Map<String, ?> body) {
		try {
			int id = Integer.parseInt(body.get("user_id").toString());
			int colorsize_id = Integer.parseInt(body.get("colorsize_id").toString());
			return new CartDAO().deleteCartByID(id, colorsize_id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
