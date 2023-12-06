package com.project.stationeryStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.stationeryStore.domain.inventory.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{

	@Query(nativeQuery = true, value = "SELECT * FROM order_details od where od.order_id =:orderId")
	List<OrderDetails> findOrderDetailsByOrderId(Integer orderId);
	
}
