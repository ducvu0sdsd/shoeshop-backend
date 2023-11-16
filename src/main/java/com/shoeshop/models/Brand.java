package com.shoeshop.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Brand")
public class Brand implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String brandName;
	@Column(columnDefinition = "TEXT")
	private String logo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "Brand [id=" + id + ", brandName=" + brandName + ", logo=" + logo + "]";
	}
	public Brand(int id, String brandName, String logo) {
		super();
		this.id = id;
		this.brandName = brandName;
		this.logo = logo;
	}
	public Brand(int id) {
		super();
		this.id = id;
	}
	public Brand(String brandName, String logo) {
		super();
		this.brandName = brandName;
		this.logo = logo;
	}
	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
