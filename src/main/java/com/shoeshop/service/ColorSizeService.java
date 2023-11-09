package com.shoeshop.service;

import java.util.Map;

import com.shoeshop.models.ColorSize;

public interface ColorSizeService {
	
	public ColorSize getColorSizeByColorAndSize(String color, String size);

}
