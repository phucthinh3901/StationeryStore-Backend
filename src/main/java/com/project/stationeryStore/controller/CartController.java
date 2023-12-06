package com.project.stationeryStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.stationeryStore.common.Constants;
import com.project.stationeryStore.domain.dto.CartDto;
import com.project.stationeryStore.domain.payload.request.CartRequest;
import com.project.stationeryStore.domain.payload.request.RemoveCartRequest;
import com.project.stationeryStore.domain.payload.response.ApiResponse;
import com.project.stationeryStore.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping()
	private ResponseEntity<ApiResponse> saveCart(@RequestBody CartRequest request){
		 final List<CartDto> result = cartService.createCart(request);
		 return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,result,Constants.CREATE_SUCCESS_MSG));
	}
	@GetMapping("/{cartByUserId}")
	public ResponseEntity<ApiResponse> getProductByUserId(@PathVariable Integer cartByUserId) {
		final List<CartDto> result = cartService.getCartByUserId(cartByUserId);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, result, Constants.GET_SUCESS_MSG));
	}
	@PostMapping("/removeProductInCart")
	public ResponseEntity<ApiResponse> removeProductInCart(@RequestBody RemoveCartRequest request){
		final List<CartDto> result = cartService.removeProductInCartByProductId(request);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, result, Constants.DELETE_SUCCESS_MSG));
	}
}