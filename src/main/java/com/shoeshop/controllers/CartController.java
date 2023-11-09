package com.shoeshop.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoeshop.models.Cart;
import com.shoeshop.service.CartService;

@RestController
public class CartController extends JwtAuthenticationController {
	
	@Autowired
	private CartService cartService;
	
	// Nhung
	@PostMapping("/cart/insert-cart") 
	public boolean insertCart(@RequestBody Map<String, ?> body) {
//		thông tin từ client gửi đến là : user_id, color, size, quantity
		return cartService.insertCart(body);
	}
	
	// Phuc
	@PostMapping("/cart/delete-cart-by-id") 
	public boolean deleteCart(@RequestBody Map<String, ?> body) {
//		thông tin từ client gửi đến là : cart_id
		return cartService.deleteCart(body);
	}
	
	// Nhat
	@GetMapping("/cart/get-all-cart-by-user")
	public List<Cart> getAllCartByUser(@RequestParam Map<String, ?> body) {
//		thông tin từ client gửi đến là : user_id
		return cartService.getAllCartByUser(body);
	}
	
	@PutMapping("/cart/update-quantity-by-id")
	public boolean updateQuantityByID (@RequestBody Map<String, ?> body) {
		return cartService.updateQuantityByID(body);
	}
}
