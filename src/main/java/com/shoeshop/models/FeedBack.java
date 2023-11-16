package com.shoeshop.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FeedBack")
public class FeedBack {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "LONGTEXT")
	private String content;
	@ManyToOne
	private UserOne user;
	@ManyToOne
	private Product product;
	private Date datetime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserOne getUser() {
		return user;
	}
	public void setUser(UserOne user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "FeedBack [id=" + id + ", content=" + content + ", user=" + user + ", product=" + product + "]";
	}
	public FeedBack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FeedBack(int id) {
		super();
		this.id = id;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public FeedBack(int id, String content, UserOne user, Product product, Date datetime) {
		super();
		this.id = id;
		this.content = content;
		this.user = user;
		this.product = product;
		this.datetime = datetime;
	}
	public FeedBack(String content, UserOne user, Product product, Date datetime) {
		super();
		this.content = content;
		this.user = user;
		this.product = product;
		this.datetime = datetime;
	}
	
}
