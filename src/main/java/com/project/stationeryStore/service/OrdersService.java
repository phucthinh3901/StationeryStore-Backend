package com.project.stationeryStore.service;

import java.util.List;

import com.project.stationeryStore.domain.dto.OrderDto;
import com.project.stationeryStore.domain.inventory.Orders;
import com.project.stationeryStore.domain.payload.request.OrderByOptionRequest;
import com.project.stationeryStore.domain.payload.request.StatusOrderRequest;

public interface OrdersService {

	List<OrderDto> createrOrderInCart(OrderByOptionRequest request);
	
	List<OrderDto> getOrderByUserId(Integer orderByUserId);
	
	List<OrderDto> updateOrderStatus(StatusOrderRequest request);
	
	List<Orders> getListOrder();
	
	List<OrderDto> getDetailOrder(Integer orderId);
	
	List<Orders> filtListStutusOrder(String status);
}
