package com.shoeshop.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoeshop.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService service;
	
	@PostMapping("/payment/order-from-client")
	public boolean OrderFromClient(@RequestBody Map<String, Object> body) {
		return service.HandleOrderFromClient(body);
	}
	
	@PostMapping("/payment/order-from-guest")
	public boolean OrderFromGuest(@RequestBody Map<String, Object> body) {
		return service.handleOrderFromGuest(body);
	}
	
	@PostMapping("/payment/order-from-cart-of-client")
	public boolean OrderFromCartOfClient(@RequestBody Map<String, Object> body) {
		return service.handleOrderFromCartOfClient(body);
	}
}
