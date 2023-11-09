package com.shoeshop.service;

import java.util.Map;

public interface PaymentService {
	public boolean HandleOrderFromClient(Map<String, Object> body);
	public boolean handleOrderFromGuest(Map<String, Object> body);
	public boolean handleOrderFromCartOfClient(Map<String, Object> body);
}
