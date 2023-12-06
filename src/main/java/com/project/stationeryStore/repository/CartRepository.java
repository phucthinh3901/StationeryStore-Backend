package com.project.stationeryStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.stationeryStore.domain.inventory.Carts;

public interface CartRepository extends JpaRepository<Carts, Integer>{

	@Query("From Carts c where c.id.users.id = :userId And c.id.products.id = :productId")
	Carts findProductInCartByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);
	
	@Query("From Carts c where c.id.users.id = :userId")
	List<Carts>findProductInCartByUserId(@Param("userId") Integer userId);
	
	
}