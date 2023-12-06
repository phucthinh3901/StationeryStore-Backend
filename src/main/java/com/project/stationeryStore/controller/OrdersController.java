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
import com.project.stationeryStore.domain.dto.OrderDto;
import com.project.stationeryStore.domain.inventory.Orders;
import com.project.stationeryStore.domain.payload.request.OrderByOptionRequest;
import com.project.stationeryStore.domain.payload.request.StatusOrderRequest;
import com.project.stationeryStore.domain.payload.response.ApiResponse;
import com.project.stationeryStore.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	
	@PostMapping("/createOrderIncart")
	public ResponseEntity<ApiResponse> createOrderByOption(@RequestBody OrderByOptionRequest request){
		final List<OrderDto> result = ordersService.createrOrderInCart(request);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, result, Constants.CREATE_SUCCESS_MSG));
	}
	@GetMapping("/{orderByUserId}")
	public ResponseEntity<ApiResponse> getProductByUserId(@PathVariable Integer orderByUserId) {
		final List<OrderDto> result = ordersService.getOrderByUserId(orderByUserId);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, result, Constants.GET_SUCESS_MSG));
	}
	@PostMapping("/updateStatusOrder")
	public ResponseEntity<ApiResponse> updateStatusOrder(@RequestBody StatusOrderRequest request){
		final List<OrderDto> result = ordersService.updateOrderStatus(request);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, result, Constants.CREATE_SUCCESS_MSG));
	}
	@GetMapping("/getListOrderByAdmin")
	public ResponseEntity<ApiResponse> getListOrder(){
		final List<Orders> result = ordersService.getListOrder();
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, result, Constants.CREATE_SUCCESS_MSG));
	}
	@GetMapping("{getDetailOrder}")
	public ResponseEntity<ApiResponse> getDetailOrder(@PathVariable Integer orderByUserId){
		final List<OrderDto> result = ordersService.getDetailOrder(orderByUserId);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, result, Constants.CREATE_SUCCESS_MSG));
	}
	@GetMapping("/orderStatus/{status}")
	public ResponseEntity<ApiResponse> filtListStutusOrder(@PathVariable String status){
		final List<Orders> result = ordersService.filtListStutusOrder(status);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, result, Constants.CREATE_SUCCESS_MSG));
	}
	
}
