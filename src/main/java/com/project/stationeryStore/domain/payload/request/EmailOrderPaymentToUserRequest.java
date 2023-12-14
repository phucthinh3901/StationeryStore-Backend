package com.project.stationeryStore.domain.payload.request;

import java.util.Date;

import lombok.Data;

@Data
public class EmailOrderPaymentToUserRequest {

	private String userName;
	
	private Double amount;
	
	private Integer orderId;
	
	private Date createAt;
	
	private String toEmail;
}
