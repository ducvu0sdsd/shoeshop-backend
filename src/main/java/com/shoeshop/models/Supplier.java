package com.shoeshop.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Supplier")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String supplierName;
	private String supplierAddress;
	private String supplierPhone;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierAddress() {
		return supplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	public String getSupplierPhone() {
		return supplierPhone;
	}
	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
	@Override
	public String toString() {
		return "Supplier [id=" + id + ", supplierName=" + supplierName + ", supplierAddress=" + supplierAddress
				+ ", supplierPhone=" + supplierPhone + "]";
	}
	public Supplier(int id, String supplierName, String supplierAddress, String supplierPhone) {
		super();
		this.id = id;
		this.supplierName = supplierName;
		this.supplierAddress = supplierAddress;
		this.supplierPhone = supplierPhone;
	}
	public Supplier(String supplierName, String supplierAddress, String supplierPhone) {
		super();
		this.supplierName = supplierName;
		this.supplierAddress = supplierAddress;
		this.supplierPhone = supplierPhone;
	}
	public Supplier(int id) {
		super();
		this.id = id;
	}
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
