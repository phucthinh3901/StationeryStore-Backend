package com.project.stationeryStore.domain.payload.request;

import lombok.Data;

@Data
public class ProductRequest {
	
	private Integer id;
	
	private Integer categoryId;
	
	private String CategoryName;
	
	private Integer brandId;
	
	private String brandName;
	
	private String productName;
	
	private String description;
	
	private Integer quantity;
	
	private Integer SoldQuantity;
	
	private Float price;
	
	private Integer discount;
}
