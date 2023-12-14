package com.project.stationeryStore.domain.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayRequest {

	private Double amount;
	
	private String bankCode;	
	
	private String content;
	
	private Integer userId;
	
	private Integer orderId;
}
