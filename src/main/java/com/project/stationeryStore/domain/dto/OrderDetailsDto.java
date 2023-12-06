package com.project.stationeryStore.domain.dto;

import lombok.Data;

@Data
public class OrderDetailsDto {

	private Integer orderDetaiId;
	
	private Integer discount;
	
	private Integer orderId;
	
	private Integer quantity;
	
	private Float price;
}
