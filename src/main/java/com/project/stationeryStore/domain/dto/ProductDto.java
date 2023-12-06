package com.project.stationeryStore.domain.dto;

import java.util.List;

import com.project.stationeryStore.domain.inventory.Images;

import lombok.Data;

@Data
public class ProductDto {

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
	
	private Boolean isActive;
	
	private Float amount;
	
	private List<Images> images;
}
