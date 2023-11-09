package com.shoeshop.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "Product")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String name;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String overview;
	private String category;
	@ManyToOne
	private Brand brand;
	@ManyToOne
	private Supplier supplier;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", overview=" + overview + ", category=" + category + ", brand="
				+ brand + ", supplier=" + supplier + "]";
	}
	public Product(int id, String name, String overview, String category, Brand brand, Supplier supplier) {
		super();
		this.id = id;
		this.name = name;
		this.overview = overview;
		this.category = category;
		this.brand = brand;
		this.supplier = supplier;
	}
	public Product(String name, String overview, String category, Brand brand, Supplier supplier) {
		super();
		this.name = name;
		this.overview = overview;
		this.category = category;
		this.brand = brand;
		this.supplier = supplier;
	}
	public Product(int id) {
		super();
		this.id = id;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
