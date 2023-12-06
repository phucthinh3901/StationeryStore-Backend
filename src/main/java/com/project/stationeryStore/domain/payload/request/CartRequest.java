package com.project.stationeryStore.domain.payload.request;

import lombok.Data;

@Data
public class CartRequest {

	private Integer userId;
	
	private Integer productId;
	
	private Integer quantity;
}
