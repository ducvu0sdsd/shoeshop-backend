package com.shoeshop.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private UserOne user;
	@ManyToOne
	private ColorSize colorSize;
	private int quantity;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserOne getUser() {
		return user;
	}
	public void setUser(UserOne user) {
		this.user = user;
	}
	public ColorSize getColorSize() {
		return colorSize;
	}
	public void setColorSize(ColorSize colorSize) {
		this.colorSize = colorSize;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Cart(int id, UserOne user, ColorSize colorSize, int quantity) {
		super();
		this.id = id;
		this.user = user;
		this.colorSize = colorSize;
		this.quantity = quantity;
	}
	public Cart(UserOne user, ColorSize colorSize, int quantity) {
		super();
		this.user = user;
		this.colorSize = colorSize;
		this.quantity = quantity;
	}
	public Cart(int id) {
		super();
		this.id = id;
	}
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", colorSize=" + colorSize + ", quantity=" + quantity + "]";
	}
	
	
}
