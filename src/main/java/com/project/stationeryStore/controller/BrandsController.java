package com.project.stationeryStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.stationeryStore.common.Constants;
import com.project.stationeryStore.domain.dto.BrandsDto;
import com.project.stationeryStore.domain.payload.request.BrandRequest;
import com.project.stationeryStore.domain.payload.response.ApiResponse;
import com.project.stationeryStore.service.BrandsService;

@RestController
@RequestMapping("/brands")
public class BrandsController {
	
	
	@Autowired
	BrandsService brandsService;
	
	@PostMapping()
	private ResponseEntity<ApiResponse> saveBrand(@RequestBody BrandRequest request){
		final List<BrandsDto> result = brandsService.createOrUpdate(request);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,result,Constants.CREATE_SUCCESS_MSG));
	}
	@DeleteMapping("/{id}")
	private ResponseEntity<ApiResponse> removeBrand(@PathVariable Integer id){
		final Boolean result = brandsService.removeBrand(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,result,Constants.DELETE_SUCCESS_MSG));
	}
	
	
}
