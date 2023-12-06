package com.project.stationeryStore.service;

import java.util.List;

import com.project.stationeryStore.domain.dto.CategoryDto;
import com.project.stationeryStore.domain.payload.request.CategoryRequest;

public interface CategoryService {
 
	List<CategoryDto> createOrUpdateCategory(CategoryRequest request);
	
	List<CategoryDto> getCategories();
	
	Boolean removeCategory(Integer id);
}
