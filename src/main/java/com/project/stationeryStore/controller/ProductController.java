package com.project.stationeryStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.stationeryStore.common.Constants;
import com.project.stationeryStore.domain.dto.ProductDto;
import com.project.stationeryStore.domain.payload.request.ProductRequest;
import com.project.stationeryStore.domain.payload.response.ApiResponse;
import com.project.stationeryStore.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping()
	private ResponseEntity<ApiResponse> saveProduct(@RequestParam(value ="file", required = false) MultipartFile[] files ,@RequestParam(value ="data", required = false) String productRequest) throws JsonMappingException, JsonProcessingException{
		
		ObjectMapper mapper = new ObjectMapper();
		ProductRequest request = null;
		if(productRequest != null) {
			request = mapper.readValue(productRequest, ProductRequest.class);
		}
		ApiResponse result = productService.createOrUpdateProduct(files,request);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping()
	private ResponseEntity<ApiResponse> paginationProduct(){
		final List<ProductDto> result = productService.getProducts();
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,result,Constants.GET_SUCESS_MSG));
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<ApiResponse> removeProduct(@PathVariable Integer id){
		final Boolean result = productService.removeProduct(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,result,Constants.DELETE_SUCCESS_MSG));
	}
	@GetMapping("/{productId}")
	public ResponseEntity<ApiResponse> getProductByUserId(@PathVariable Integer productId) {
		final List<ProductDto> result = productService.getProductById(productId);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, result, Constants.GET_SUCESS_MSG));
	}
	
}
