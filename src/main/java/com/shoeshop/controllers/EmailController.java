package com.shoeshop.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoeshop.service.EmailService;

@RestController
@RequestMapping("/api/v1")
public class EmailController {
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/emails/verify-email")
	public String sendEmailVerify(@RequestBody Map<String, ?> body) {
		String code = emailService.sendEmailVerify(body);
		return code;
	}
	
	@PostMapping("/emails/notice-of-successful-account-creation")
	public void sendEmailNoticeOfSuccessfulAccountCreation(@RequestBody Map<String, ?> body) {
		emailService.sendEmailNoticeOfSuccessfulAccountCreation(body);
	}
	
	@PostMapping("/emails/notice-of-successful-order")
	public void sendEmailNoticeOfSuccessfulOrder(@RequestBody Map<String, ?> body) {
		emailService.sendEmailNoticeOfSuccessfulOrder(body);
	}
}
