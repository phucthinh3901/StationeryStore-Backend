package com.project.stationeryStore.domain.payload.request;

import lombok.Data;

@Data
public class RemoveFeedbackRequest {

	private Integer userId;
	
	private Integer productId;
	
	private Integer orderId;
}
