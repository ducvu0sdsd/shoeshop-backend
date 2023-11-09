package com.shoeshop.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ColorSize")
public class ColorSize {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double retailPrice;
	private double importPrice;
	private String color;
	private int size;
	private int quantity;
	
	@ManyToOne
	private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(double importPrice) {
		this.importPrice = importPrice;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ColorSize [id=" + id + ", retailPrice=" + retailPrice + ", importPrice=" + importPrice + ", color="
				+ color + ", size=" + size + ", quantity=" + quantity + ", product=" + product + "]";
	}

	public ColorSize(int id, double retailPrice, double importPrice, String color, int size, int quantity,
			Product product) {
		super();
		this.id = id;
		this.retailPrice = retailPrice;
		this.importPrice = importPrice;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		this.product = product;
	}
	
	public ColorSize(String color, int size, int quantity, Product product) {
		super();
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		this.product = product;
	}
	
	public ColorSize(double retailPrice, double importPrice, String color, int size, int quantity,
			Product product) {
		super();
		this.retailPrice = retailPrice;
		this.importPrice = importPrice;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		this.product = product;
	}
	
	public ColorSize(int id) {
		super();
		this.id = id;
	}

	public ColorSize() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
