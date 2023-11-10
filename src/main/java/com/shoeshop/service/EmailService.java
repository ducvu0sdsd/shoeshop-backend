package com.shoeshop.service;

import java.util.Map;

import com.shoeshop.models.Email;

public interface EmailService {
	public String sendEmailVerify(Map<String, ?> body);
	public void sendEmailNoticeOfSuccessfulAccountCreation(Map<String, ?> body);
	public void sendEmailNoticeOfSuccessfulOrder(Map<String, ?> body);
}
