package com.project.stationeryStore.domain.dto;

import lombok.Data;

@Data
public class CategoryDto {

	private Integer id;
	
	private String categoryName;
	
	private String description;
	
	private String imageName;
	
	private Boolean isActive;
}
