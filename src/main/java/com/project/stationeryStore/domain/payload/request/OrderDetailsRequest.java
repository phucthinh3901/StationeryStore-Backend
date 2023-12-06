package com.project.stationeryStore.domain.payload.request;

import java.awt.Image;
import java.util.List;

import lombok.Data;

@Data
public class OrderDetailsRequest {
	
	private Integer orderDetaiId;
	
	private Integer discount;
	
	private Integer orderId;
	
	private Integer quantity;
	
	private Float price;
	
	private Integer productId;
	
	private List<Image> listImage;
}
