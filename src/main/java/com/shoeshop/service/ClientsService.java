package com.shoeshop.service;

import java.util.List;
import java.util.Map;


public interface ClientsService {
	public List<Map<String, ?>> getAllClientWithNumberOfOrder();
	public boolean updateClient(Map<String, ?> body);
	public boolean deleteClient(Map<String, ?> body);
}
