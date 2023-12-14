package com.project.stationeryStore.domain.payload.request;

import lombok.Data;

@Data
public class StatusOrderRequest {

	private Integer orderId;
	
	private Integer userId;
	
	private String status;
}
