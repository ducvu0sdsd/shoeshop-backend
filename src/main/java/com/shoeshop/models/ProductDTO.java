package com.shoeshop.models;

import java.util.List;

public class ProductDTO {
	private Product product;
	private List<Image> images;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "ProductDTO [product=" + product + ", images=" + images 
				+ "]";
	}
	public ProductDTO(Product product, List<Image> images) {
		super();
		this.product = product;
		this.images = images;
	}
	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
