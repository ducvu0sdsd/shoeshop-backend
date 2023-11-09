package com.shoeshop.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.shoeshop.models.ColorSize;
import com.shoeshop.repositories.ColorSizeDAO;
import com.shoeshop.service.ColorSizeService;

@Service
public class implColorSize implements ColorSizeService{

	@Override
	public ColorSize getColorSizeByColorAndSize(String color, String size) {
		String color1 = (String)color;
		int size1 = Integer.parseInt(size);
		return new ColorSizeDAO().getItemByColorAndSize(color1, size1);
	}

}