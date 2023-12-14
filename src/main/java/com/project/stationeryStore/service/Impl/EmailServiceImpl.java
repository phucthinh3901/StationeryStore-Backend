package com.project.stationeryStore.service.Impl;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.project.stationeryStore.domain.dto.ProductOrderDto;
import com.project.stationeryStore.domain.inventory.Products;
import com.project.stationeryStore.domain.payload.EmailDetails;
import com.project.stationeryStore.domain.payload.request.EmailOrderPaymentToUserRequest;
import com.project.stationeryStore.domain.payload.request.EmailPaymentNotificationRequest;
import com.project.stationeryStore.domain.payload.request.EmailUpdateStatusRequest;
import com.project.stationeryStore.repository.ProductRepository;
import com.project.stationeryStore.service.EmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	JavaMailSender emailSender;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SpringTemplateEngine templateEngine;
	
	@Override
	public void sendSimpleMail(EmailDetails email) throws Exception {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariables(email.getProperties());
		helper.setFrom(email.getFrom());
		helper.setTo(email.getTo());
		helper.setSubject(email.getSubject());
		String html = templateEngine.process(email.getTemplate(), context);
		helper.setText(html, true);
		emailSender.send(message);
		System.out.println("Mail Send...");
	}
	@Override
	public void sendMailUPpgradeRank(EmailPaymentNotificationRequest request) {
		EmailDetails email = new EmailDetails();
		List<String> listNameProduct = new ArrayList<String>();
		List<Integer> listQuantityProduct = new ArrayList<Integer>();
		Products product = null;
		try {
			Map<String, Object> properties = new HashMap<>();
			email.setSubject("[Email thông báo đơn hàng của bạn]");
			properties.put("userName", request.getUserName());
			properties.put("amount", formatMoneyWithCurrencyVN(request.getAmount()));
			properties.put("content", request.getContent());
			properties.put("createAt", request.getCreateAt());
			for(ProductOrderDto dto : request.getProductOrderDtos()) {
				product = productRepository.findByProductById(dto.getProductId());
				listQuantityProduct.add(dto.getQuantity());
				listNameProduct.add(product.getProductName());
			}
			properties.put("productName", listNameProduct);
			properties.put("productQuantity", listQuantityProduct);
			
			email.setFrom("fromemail@gmail.com");
			email.setTemplate("notificationOrderToUser.html");
			email.setProperties(properties);
			
			String regex = "^(.+)@(.+)$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(request.getToEmail());
			if (matcher.matches()) {
				email.setTo(request.getToEmail());
				sendSimpleMail(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String formatMoneyWithCurrencyVN(Float price) {
		Locale locale = new Locale("vi", "VN");
		Currency currency = Currency.getInstance("VND");
		DecimalFormatSymbols df = DecimalFormatSymbols.getInstance(locale);
		df.setCurrency(currency);
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		numberFormat.setCurrency(currency);
		return numberFormat.format(price);
	}
	@Override
	public void sendEmailUpdateStatus(EmailUpdateStatusRequest request) {
		EmailDetails email = new EmailDetails();
		try {
			Map<String, Object> properties = new HashMap<>();
			email.setSubject("[Email thông báo đơn hàng của bạn]");
			properties.put("useName", request.getNameUser());
			properties.put("oderId", request.getOrderId());
			properties.put("status", request.getStatus());
			properties.put("createAt", request.getCreateAt());
			
			email.setFrom("fromemail@gmail.com");
			email.setTemplate("UpdateOrdetStatus.html");
			email.setProperties(properties);
			
			String regex = "^(.+)@(.+)$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(request.getToEmail());
			if (matcher.matches()) {
				email.setTo(request.getToEmail());
				sendSimpleMail(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void sendEmailOrderPaymentToUser(EmailOrderPaymentToUserRequest request) {
		EmailDetails email = new EmailDetails();
		try {
			Map<String, Object> properties = new HashMap<>();
			email.setSubject("[Email thông báo đơn hàng của bạn]");
			properties.put("useName", request.getUserName());
			properties.put("oderId", request.getOrderId());
			properties.put("createAt", request.getCreateAt());
			properties.put("amount", request.getAmount()/100);
			email.setFrom("fromemail@gmail.com");
			email.setTemplate("notificationOrderPaymentToUser.html");
			email.setProperties(properties);
			
			String regex = "^(.+)@(.+)$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(request.getToEmail());
			if (matcher.matches()) {
				email.setTo(request.getToEmail());
				sendSimpleMail(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
