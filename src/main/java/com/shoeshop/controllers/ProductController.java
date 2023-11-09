package com.shoeshop.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shoeshop.models.Brand;
import com.shoeshop.models.Image;
import com.shoeshop.models.Product;
import com.shoeshop.models.ProductDTO;
import com.shoeshop.repositories.BrandDAO;
import com.shoeshop.repositories.ImageDAO;
import com.shoeshop.repositories.ProductDAO;
import com.shoeshop.service.ProductService;

@RestController
public class ProductController extends JwtAuthenticationController {
	
	@Autowired
	private ProductService productService;	
	
	@PostMapping("/products/insert-product")
	public boolean insertProduct(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, List<String>> body) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return productService.insertProduct(body);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@PutMapping("/products/update-product")
	public boolean updateProduct(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, List<String>> body) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return productService.updateProduct(body);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@PostMapping("/products/delete-product")
	public boolean deleteProduct(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, String> body) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return productService.deleteProduct(body);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@GetMapping("/products/get-all-product")
	public List<Map<String, ?>> getAllProduct() {
		return productService.getAllProduct();
	}
	
	
	// DAO -> Service -> Controller
	// Nhung
	@PostMapping("/products/insert-feedback") 
	public boolean insertFeedBack(@RequestBody Map<String, ?> body) {
		return productService.insertFeedBack(body);
	}
	
	// Phuc Du
	@PostMapping("/products/delete-feedback")
	public boolean deleteFeedBack(@RequestBody Map<String, ?> body) {
		
		return false;
	}
	
	// Thanh Nhat //
	@GetMapping("/products/get-all-feedback-by-each-product")
	public List<Map<String, Object>> getAllFeedBackByEachProduct() {
		return productService.getAllFeedbackByEachProduct();
	}
}
