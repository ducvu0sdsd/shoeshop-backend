package com.shoeshop.service;

import java.util.List;
import java.util.Map;

import com.shoeshop.models.Cart;

public interface CartService {
	
	public boolean insertCart(Map<String, ?> body);
	public List<Cart> getAllCartByUser(Map<String, ?> body);
	public boolean updateQuantityByID(Map<String, ?> body);
	public boolean deleteCart(Map<String, ?> body);
}
