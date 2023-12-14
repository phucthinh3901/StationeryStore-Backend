package com.project.stationeryStore.domain.payload.request;

import java.util.Date;
import java.util.List;

import com.project.stationeryStore.domain.dto.ProductOrderDto;

import lombok.Data;

@Data
public class EmailPaymentNotificationRequest {

	private String userName;
	
	private Float amount;
	
	private String content;
	
	private Date createAt;
	
	private String toEmail;
	
	private List<ProductOrderDto> productOrderDtos;
	
}
