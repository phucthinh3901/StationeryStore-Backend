package com.project.stationeryStore.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FeedbackDto {

	private Integer userId;
	
	private Integer productId;
	
	private Integer orderId;
	
	private Boolean isActive;
	
	private Date createAt;
	
	private String replyAdmin;
	
	private Date relyData;
	
	private Date updateDate;
	
	private String comment;
}
