package com.project.stationeryStore.domain.payload.request;

import java.util.Date;

import lombok.Data;

@Data
public class EmailUpdateStatusRequest {

	private String toEmail;
	
	private String status;
	
	private String nameUser;
	
	private Integer orderId;
	
	private Date createAt;
}
