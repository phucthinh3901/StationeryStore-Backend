package com.project.stationeryStore.domain.dto;

import lombok.Data;

@Data
public class CartDto {

	private Integer id;
	
	private String productName;
	
	private String categoryName;
	
	private Integer discount; 
	
	private Integer quantity;
	
	private Float price; 
	
	private Float priceAfterDiscount;
}
