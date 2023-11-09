package com.shoeshop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shoeshop.models.Supplier;
import com.shoeshop.repositories.SupplierDAO;
import com.shoeshop.service.SupplierService;

@Service
public class implSupplier implements SupplierService{

	@Override
	public boolean insertSupplier(Map<String, String> body) {
		String name = body.get("name");
		String address = body.get("address");
		String phone = body.get("phone");
		Supplier s = new Supplier(name, address, phone);
		return new SupplierDAO().insert(s);
	}

	@Override
	public List<Supplier> getAllSupplier() {
		return new SupplierDAO().getAllSuppliers();
	}

	@Override
	public boolean deleteSupplier(Map<String, String> body) {
		int ma = Integer.parseInt(body.get("id"));
		return new SupplierDAO().delete(new Supplier(ma));
	}

	@Override
	public boolean updateSupplier(Map<String, String> body) {
		int ma = Integer.parseInt(body.get("id"));
		String name = body.get("name");
		String address = body.get("address");
		String phone = body.get("phone");
		Supplier s = new Supplier(ma, name, address, phone);
		return new SupplierDAO().update(new Supplier(ma, name, address, phone));
	}
	
	
}
