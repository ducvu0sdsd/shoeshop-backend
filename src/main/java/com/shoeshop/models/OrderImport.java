package com.shoeshop.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderImport")
public class OrderImport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date date;
	private String note;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "OrderImport [id=" + id + ", date=" + date + ", note=" + note + "]";
	}
	public OrderImport(int id, Date date, String note) {
		super();
		this.id = id;
		this.date = date;
		this.note = note;
	}
	public OrderImport(int id) {
		super();
		this.id = id;
	}
	public OrderImport(Date date, String note) {
		super();
		this.date = date;
		this.note = note;
	}
	public OrderImport() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
