package com.shoeshop.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface OrderService {
	public boolean insertOrderImport(Map<String, ?> body) throws ParseException;
	public boolean updateStatusOrder(Map<String, ?> body);
	public List<Map<String, Object>> getAllOrderImport();
	public List<Map<String, Object>> getAllOrderBuy();
}
