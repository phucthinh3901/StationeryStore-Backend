package com.project.stationeryStore.domain.payload.request;

import lombok.Data;

@Data
public class RemoveCartRequest {

	private Integer userId;
	
	private Integer productId;
}
