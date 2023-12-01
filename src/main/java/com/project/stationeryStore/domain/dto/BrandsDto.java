package com.project.stationeryStore.domain.dto;

import lombok.Data;

@Data
public class BrandsDto {
	
	private String name;
	
	private String description;
	
	private Integer id;
	
	private Boolean isActive;
}
