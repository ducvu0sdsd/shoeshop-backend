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
@Table(name = "OrderBuy")
public class OrderBuy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date date;
	private String status;
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String note;
	private String payMethod;
	@ManyToOne
	private UserOne user;
	@ManyToOne
	private Guest guest;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public UserOne getUser() {
		return user;
	}
	public void setUser(UserOne user) {
		this.user = user;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	@Override
	public String toString() {
		return "OrderBuy [id=" + id + ", date=" + date + ", status=" + status + ", note=" + note + ", payMethod="
				+ payMethod + ", user=" + user + ", guest=" + guest + "]";
	}
	public OrderBuy(int id, Date date, String status, String note, String payMethod, UserOne user, Guest guest) {
		super();
		this.id = id;
		this.date = date;
		this.status = status;
		this.note = note;
		this.payMethod = payMethod;
		this.user = user;
		this.guest = guest;
	}
	public OrderBuy(Date date, String status, String note, String payMethod, UserOne user, Guest guest) {
		super();
		this.date = date;
		this.status = status;
		this.note = note;
		this.payMethod = payMethod;
		this.user = user;
		this.guest = guest;
	}
	public OrderBuy(int id,String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public OrderBuy(int id) {
		super();
		this.id = id;
	}
	public OrderBuy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
}
