package com.project.stationeryStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.stationeryStore.domain.inventory.Orders;


public interface OrderRepository extends JpaRepository<Orders, Integer>{

	@Query("From Orders o where o.id = :id And o.isActive = true")
	Orders findOrderByIdAndIsActive(@Param("id") Integer id);
	
	@Query("From Orders o where o.id = :orderId And o.user.id = :userId And o.isActive = true")
	Orders findOrderByOrderIdAndUserId(@Param("orderId") Integer orderId, @Param("userId") Integer userId);
	
	@Query("From Orders o where o.status =:status And o.isActive = true")
	List<Orders> findOrderByStatus(@Param("status") String status);
}
