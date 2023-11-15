package com.shoeshop.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoeshop.repositories.BrandDAO;
import com.shoeshop.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController extends JwtAuthenticationController{
	
	@Autowired
	private OrderService service;

	@PostMapping("/orders/insert-order-import")
	public boolean insertOrderImport(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, ?> body) throws ParseException{
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return service.insertOrderImport(body);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@PutMapping("/orders/update-status-order")
	public boolean updateStatusOrder(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, ?> body) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return service.updateStatusOrder(body);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@GetMapping("/orders/get-all-order-import")
	public List<Map<String, Object>> getAllOrderImport(@RequestHeader("Authorization") String authorizationHeader) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return service.getAllOrderImport();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/orders/get-all-order-buy")
	public List<Map<String, Object>> getAllOrderBuy(@RequestHeader("Authorization") String authorizationHeader) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return service.getAllOrderBuy();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
}
