package com.shoeshop.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.shoeshop.models.Brand;
import com.shoeshop.models.Image;

public class ImageDAO extends AbtractDAO<Image> {

	public ImageDAO() {
		super(Image.class);
	}

}
