package com.shoeshop.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERONE")
public class UserOne implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String username;
	private String password;
	@Column(columnDefinition = "LONGTEXT")
	private String name;
	private String gender;
	@Column(unique = true)
	private String email;
	private String phonenumber;
	private Date dateofbirth;
	@Column(columnDefinition = "LONGTEXT")
	private String avatar;
	private boolean admin;
	@Column(columnDefinition = "LONGTEXT")
	private String address;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public UserOne(int id, String username, String password, String name, String gender, String email,
			String phonenumber, Date dateofbirth, String avatar, boolean admin, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.phonenumber = phonenumber;
		this.dateofbirth = dateofbirth;
		this.avatar = avatar;
		this.admin = admin;
		this.address = address;
	}
	public UserOne(String username, String password, String name, String gender, String email,
			String phonenumber, Date dateofbirth, String avatar, boolean admin, String address) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.phonenumber = phonenumber;
		this.dateofbirth = dateofbirth;
		this.avatar = avatar;
		this.admin = admin;
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public UserOne(int id) {
		super();
		this.id = id;
	}
	public UserOne() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserOne(String username, String password,boolean admin) {
		super();
		this.username = username;
		this.password = password;
		this.admin = admin;
	}
	public UserOne(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
}
