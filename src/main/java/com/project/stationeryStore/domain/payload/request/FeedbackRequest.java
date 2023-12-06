package com.project.stationeryStore.domain.payload.request;

import java.util.Date;

import lombok.Data;

@Data
public class FeedbackRequest {

	private Integer userId;
	
	private Integer productId;

	private Integer orderId;
	
	private String comment;
	
	private String replyAdmin;
	
	private Date relyData;
	
}
