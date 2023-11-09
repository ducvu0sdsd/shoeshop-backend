package com.shoeshop.service;

import java.util.List;
import java.util.Map;

import com.shoeshop.models.Supplier;

public interface SupplierService {
	public boolean insertSupplier(Map<String, String> body);
	public List<Supplier> getAllSupplier();
	public boolean deleteSupplier(Map<String, String> body);
	public boolean updateSupplier(Map<String, String> body);
}
