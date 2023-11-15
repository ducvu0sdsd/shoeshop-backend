package com.shoeshop.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoeshop.models.ColorSize;
import com.shoeshop.service.ColorSizeService;

@RestController
@RequestMapping("/api/v1")
public class ColorSizeController {

	@Autowired
	private ColorSizeService service;
	
	@GetMapping("/colors-sizes/get-color-size-by-color-and-size")
	public ColorSize getColorSizeByColorAndSize(@RequestParam String color, @RequestParam String size) {
		return service.getColorSizeByColorAndSize(color, size);
	}
	
}
