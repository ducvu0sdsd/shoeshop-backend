package com.shoeshop.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shoeshop.models.Supplier;
import com.shoeshop.service.SupplierService;

@RestController
public class SupplierController extends JwtAuthenticationController{
	
	@Autowired
	private SupplierService service;
	
	@PostMapping("/supplier/insert-supplier") 
	public boolean insertSupplier (@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, String> body) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return service.insertSupplier(body);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@PutMapping("/supplier/update-supplier") 
	public boolean updateSupplier (@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, String> body) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return service.updateSupplier(body);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@PostMapping("/supplier/delete-supplier") 
	public boolean deleteSupplier (@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, String> body) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return service.deleteSupplier(body);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@GetMapping("/supplier/get-all-suppliers")
	public List<Supplier> getAllSuppliers(@RequestHeader("Authorization") String authorizationHeader) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return service.getAllSupplier();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
}
