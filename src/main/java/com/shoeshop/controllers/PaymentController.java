package com.shoeshop.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoeshop.service.PaymentService;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

	@Autowired
	private PaymentService service;
	
	@PostMapping("/payments/order-from-client")
	public boolean OrderFromClient(@RequestBody Map<String, Object> body) {
		return service.HandleOrderFromClient(body);
	}
	
	@PostMapping("/payments/order-from-guest")
	public boolean OrderFromGuest(@RequestBody Map<String, Object> body) {
		return service.handleOrderFromGuest(body);
	}
	
	@PostMapping("/payments/order-from-cart-of-client")
	public boolean OrderFromCartOfClient(@RequestBody Map<String, Object> body) {
		return service.handleOrderFromCartOfClient(body);
	}
}
