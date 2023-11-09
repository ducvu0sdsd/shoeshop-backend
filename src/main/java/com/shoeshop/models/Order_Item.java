package com.shoeshop.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Order_Item")
public class Order_Item implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int quantity;
	@OneToOne
	private ColorSize colorSize;

	@ManyToOne
	private OrderImport orderImport;

	@ManyToOne
	private OrderBuy orderBuy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ColorSize getColorSize() {
		return colorSize;
	}

	public void setColorSize(ColorSize colorSize) {
		this.colorSize = colorSize;
	}

	public OrderImport getOrderImport() {
		return orderImport;
	}

	public void setOrderImport(OrderImport orderImport) {
		this.orderImport = orderImport;
	}

	public OrderBuy getOrderBuy() {
		return orderBuy;
	}

	public void setOrderBuy(OrderBuy orderBuy) {
		this.orderBuy = orderBuy;
	}

	@Override
	public String toString() {
		return "Order_Item [id=" + id + ", quantity=" + quantity + ", colorSize=" + colorSize + ", orderImport="
				+ orderImport + ", orderBuy=" + orderBuy + "]";
	}

	public Order_Item(int id, int quantity, ColorSize colorSize, OrderImport orderImport, OrderBuy orderBuy) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.colorSize = colorSize;
		this.orderImport = orderImport;
		this.orderBuy = orderBuy;
	}
	
	public Order_Item(int id) {
		super();
		this.id = id;
	}
	
	public Order_Item(int quantity, ColorSize colorSize, OrderImport orderImport, OrderBuy orderBuy) {
		super();
		this.quantity = quantity;
		this.colorSize = colorSize;
		this.orderImport = orderImport;
		this.orderBuy = orderBuy;
	}

	public Order_Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
} 
