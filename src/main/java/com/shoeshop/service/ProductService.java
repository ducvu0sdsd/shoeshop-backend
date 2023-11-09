package com.shoeshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.shoeshop.models.Brand;

public interface ProductService {
	
	public boolean insertProduct(Map<String, List<String>> body);
	
	public boolean updateProduct(Map<String, List<String>> body);
	
	public boolean deleteProduct(Map<String, String> body);
	
	public List<Map<String,?>> getAllProduct();
	
	public boolean insertFeedBack(Map<String, ?> body);
	public List<Map<String,Object>> getAllFeedbackByEachProduct();
}
