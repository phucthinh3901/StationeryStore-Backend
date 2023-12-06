package com.project.stationeryStore.domain.payload.request;

import lombok.Data;

@Data
public class CategoryRequest {

	private Integer id;
	
	private String categoryName;
	
	private String description;
	
	private String imageName;
	
}
