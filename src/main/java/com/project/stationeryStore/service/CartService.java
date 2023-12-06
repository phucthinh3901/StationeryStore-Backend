package com.project.stationeryStore.service;

import java.util.List;

import com.project.stationeryStore.domain.dto.CartDto;
import com.project.stationeryStore.domain.payload.request.CartRequest;
import com.project.stationeryStore.domain.payload.request.RemoveCartRequest;

public interface CartService {

	List<CartDto> createCart(CartRequest request);
	
	List<CartDto> getCartByUserId(Integer cartByUserId);
	
	List<CartDto> removeProductInCartByProductId(RemoveCartRequest request);
}
