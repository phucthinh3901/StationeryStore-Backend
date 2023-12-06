package com.project.stationeryStore.domain.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
	
	private Integer id;
	
	private String address;
	
	private Boolean isActive;
	
	private Float totalBill;
	
	private Date updateAt;
	
	private Integer userId;
	
	private Date createAt;
	
	private Integer discountVip;
	
	private Integer updateBy;
	
	private String description;
	
	private String payment;
	
	private String Status;
	
	private Integer productNumber;
	
	private List<OrderDetailsDto> orderDetailsDto;
	
}
