package com.project.stationeryStore.service;

import java.util.List;

import com.project.stationeryStore.domain.dto.BrandsDto;
import com.project.stationeryStore.domain.payload.request.BrandRequest;

public interface BrandsService {
	
	List<BrandsDto> createOrUpdate(BrandRequest request);
	
	List<BrandsDto> getBrands();
	
	Boolean removeBrand(Integer id);
}
