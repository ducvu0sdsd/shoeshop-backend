package com.shoeshop.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Image")
public class Image implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(columnDefinition = "TEXT")
	private String image;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "Image [id=" + id + ", image=" + image + ", product=" + product + "]";
	}
	public Image(long id, String image, Product product) {
		super();
		this.id = id;
		this.image = image;
		this.product = product;
	}
	public Image(String image, Product product) {
		super();
		this.image = image;
		this.product = product;
	}
	public Image(String image) {
		super();
		this.image = image;
	}
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
