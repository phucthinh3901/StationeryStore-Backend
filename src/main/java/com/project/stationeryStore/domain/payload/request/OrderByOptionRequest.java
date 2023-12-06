package com.project.stationeryStore.domain.payload.request;

import java.util.List;

import com.project.stationeryStore.domain.dto.ProductOrderDto;

import lombok.Data;

@Data
public class OrderByOptionRequest {
	
	List<ProductOrderDto> listProduct;
	
	private Integer userId;
	
	private String address;

	private Integer discountVip;
	
	private Integer updateBy;

	private String description;

	private String payment;
	
	private String Status;

	private String remark;
}

