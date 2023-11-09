package com.shoeshop.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String sessionToken;
	@OneToOne
	private UserOne user;
	private Date expires;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	public UserOne getUser() {
		return user;
	}
	public void setUser(UserOne user) {
		this.user = user;
	}
	public Date getExpires() {
		return expires;
	}
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	@Override
	public String toString() {
		return "Session [id=" + id + ", sessionToken=" + sessionToken + ", user=" + user + ", expires=" + expires + "]";
	}
	public Session(int id, String sessionToken, UserOne user, Date expires) {
		super();
		this.id = id;
		this.sessionToken = sessionToken;
		this.user = user;
		this.expires = expires;
	}
	public Session(String sessionToken, UserOne user, Date expires) {
		super();
		this.sessionToken = sessionToken;
		this.user = user;
		this.expires = expires;
	}
	public Session(int id) {
		super();
		this.id = id;
	}
	public Session() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
