package com.project.stationeryStore.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.stationeryStore.domain.dto.ProductDto;
import com.project.stationeryStore.domain.payload.request.ProductRequest;
import com.project.stationeryStore.domain.payload.response.ApiResponse;

public interface ProductService {

	ApiResponse createOrUpdateProduct(MultipartFile[] files , ProductRequest request);
	
	List<ProductDto> getProducts();
	
	Boolean removeProduct(Integer id);
	
	List<ProductDto> getProductById(Integer productId);
	
	
}
