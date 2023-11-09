package com.shoeshop.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shoeshop.models.UserOne;
import com.shoeshop.repositories.BrandDAO;
import com.shoeshop.service.ClientsService;

@RestController
public class ClientController extends JwtAuthenticationController{
	
	@Autowired
	private ClientsService clientsService;
	
	@GetMapping("/clients/get-all-client-with-number-of-order")
	public List<Map<String, ?>> getAllClientWithNumberOfOrder(@RequestHeader("Authorization") String authorizationHeader) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return clientsService.getAllClientWithNumberOfOrder();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping("/clients/update-client")
	public boolean updateClient(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, ?> body) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return clientsService.updateClient(body);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@PostMapping("/clients/delete-client")
	public boolean deleteClient(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, ?> body) {
		try {
			boolean isAuth = validateTokenForAdmin(authorizationHeader);
			if (isAuth == true) {
				return clientsService.deleteClient(body);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
