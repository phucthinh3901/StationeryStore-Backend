package com.project.stationeryStore.domain.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

	private Boolean success;
	
	private Object data;
	
	private String message;
	
}
