package com.shoeshop.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

import com.shoeshop.models.Email;
import com.shoeshop.service.EmailService;

@Service
public class implEmail implements EmailService{

	@Autowired
	private JavaMailSender mailSender = new JavaMailSenderImpl();
	private int totalPrice;
	private String content;
	
	public String createVerifyCode(){
		Random random = new Random();
        int randomNumber = random.nextInt(10000);
        String randomString = String.format("%04d", randomNumber);
        return randomString;
	}
	
	private String readHtmlFile(String fileName) throws IOException {
	    Resource resource = new ClassPathResource("templates/" + fileName);
	    try (InputStream inputStream = resource.getInputStream()) {
	        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
	        StringBuilder contentBuilder = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            contentBuilder.append(line).append("\n");
	        }
	        return contentBuilder.toString();
	    }
	}
	
	@Override
	public String sendEmailVerify(Map<String, ?> body) {
	    MimeMessage message = mailSender.createMimeMessage();
	    try {
	        String code = createVerifyCode();
	        String toEmail = body.get("toEmail").toString();
	        Email email = new Email();
	        email.setToEmail(toEmail);
	        email.setSubject("Verify Registration Information");
	        String htmlContent = readHtmlFile("verification-email.html");
	        htmlContent = htmlContent.replace("{verificationCode}", code);
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom("icgaming26zs@gmail.com");
	        helper.setTo(email.getToEmail());
	        helper.setSubject(email.getSubject());
	        helper.setText(htmlContent, true);
	        mailSender.send(message);
	        return code;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public void sendEmailNoticeOfSuccessfulAccountCreation(Map<String, ?> body) {
		MimeMessage message = mailSender.createMimeMessage();
	    try {
	    	String username = body.get("username").toString();
	    	String name = body.get("name").toString();
	    	String phone = body.get("phone").toString();
	    	String address = body.get("address").toString();
	        String toEmail = body.get("toEmail").toString();
	        Email email = new Email();
	        email.setToEmail(toEmail);
	        email.setSubject("Notification Of Successful Account Creation");
	        String htmlContent = readHtmlFile("successfull-creation-email.html");
	        htmlContent = htmlContent.replace("{username}",username);
	        htmlContent = htmlContent.replace("{name}", name);
	        htmlContent = htmlContent.replace("{phone}", phone);
	        htmlContent = htmlContent.replace("{address}", address);
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom("icgaming26zs@gmail.com");
	        helper.setTo(email.getToEmail());
	        helper.setSubject(email.getSubject());
	        helper.setText(htmlContent, true);
	        mailSender.send(message);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void sendEmailNoticeOfSuccessfulOrder(Map<String, ?> body) {
		MimeMessage message = mailSender.createMimeMessage();
	    try {
	        String toEmail = body.get("toEmail").toString();
	        String name = body.get("name").toString();
	        List<Map<String, ?>> maps = (List<Map<String, ?>>) body.get("products");
	        Email email = new Email();
	        email.setToEmail(toEmail);
	        email.setSubject("Notification Of Your Successful Order");
	        String htmlContent = readHtmlFile("successful-order-email.html");
	        content = "";
	        totalPrice = 0;
	        maps.forEach(item -> {
	        	String nameProduct = (String) item.get("name");
	        	String size = (String) item.get("size");
	        	String color = (String) item.get("color");
	        	double price =  Double.parseDouble(item.get("price").toString());
	        	int quantity = Integer.parseInt(item.get("quantity").toString());
	        	double total = price * quantity;
	        	totalPrice += total;
	        	String html = "<div style='display: flex;align-items: center;margin: 5px 0;'>\r\n"
	        			+ "        <b style='margin: 0 5px;display: flex;font-size: 17px;align-items: center;'>"+nameProduct+" - "+size+" - <div style='margin: 0 5px;height: 20px;width: 20px;background-color: "+color+";'></div> x 3 : <span style='color: red;margin: 0 5px;'>"+total+" $</span></b>\r\n"
	        			+ "    </div>";
	        	content = content + html;
	        });
	        htmlContent = htmlContent.replace("{name}",name);
	        htmlContent = htmlContent.replace("{shoeitem}",content);
	        htmlContent = htmlContent.replace("{total}",totalPrice + "");
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom("icgaming26zs@gmail.com");
	        helper.setTo(email.getToEmail());
	        helper.setSubject(email.getSubject());
	        helper.setText(htmlContent, true);
	        mailSender.send(message);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
