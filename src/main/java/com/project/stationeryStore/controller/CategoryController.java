package com.project.stationeryStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.stationeryStore.common.Constants;
import com.project.stationeryStore.domain.dto.CategoryDto;
import com.project.stationeryStore.domain.payload.request.CategoryRequest;
import com.project.stationeryStore.domain.payload.response.ApiResponse;
import com.project.stationeryStore.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping()
	private ResponseEntity<ApiResponse> saveCategory(@RequestBody CategoryRequest request){
		final List<CategoryDto> result = categoryService.createOrUpdateCategory(request);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,result,Constants.CREATE_SUCCESS_MSG));
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<ApiResponse> removeBrand(@PathVariable Integer id){
		final Boolean result = categoryService.removeCategory(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,result,Constants.DELETE_SUCCESS_MSG));
	}
	@GetMapping()
	private ResponseEntity<ApiResponse> paginationCategory(){
		final List<CategoryDto> result = categoryService.getCategories();
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,result,Constants.GET_SUCESS_MSG));
	}
}
