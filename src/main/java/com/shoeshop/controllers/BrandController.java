package com.shoeshop.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoeshop.models.Brand;
import com.shoeshop.models.UserOne;
import com.shoeshop.repositories.BrandDAO;
import com.shoeshop.repositories.UserDAO;

import antlr.collections.List;

@RestController
@RequestMapping("/api/v1")
public class BrandController extends JwtAuthenticationController {
	
	@PostMapping("/brands/insert-brand") 
	public boolean insertBrand (@RequestHeader("Authorization") String authorizationHeader, @RequestBody Brand brand) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				Boolean result = new BrandDAO().insert(brand);
				return result;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@PutMapping("/brands/update-brand") 
	public boolean updateBrand (@RequestHeader("Authorization") String authorizationHeader, @RequestBody Brand brand) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				Boolean result = new BrandDAO().update(brand);
				return result;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@PostMapping("/brands/delete-brand") 
	public boolean deleteBrand (@RequestHeader("Authorization") String authorizationHeader,@RequestBody Brand brand) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			System.out.println(isAuth);
			if (isAuth == true) {
				Boolean result = new BrandDAO().delete(brand);
				return result;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@GetMapping("/brands/get-all-brands")
	public java.util.List<Brand> getAllBrand() {
		java.util.List<Brand> brands = new ArrayList<>();
		try {
			brands = new BrandDAO().getAllBrand();
			return brands;
		} catch (Exception e) {
			return brands;
		}
	}
}
