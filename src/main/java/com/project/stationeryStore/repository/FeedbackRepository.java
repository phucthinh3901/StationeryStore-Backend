package com.project.stationeryStore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.stationeryStore.domain.inventory.Feedbacks;

public interface FeedbackRepository extends JpaRepository<Feedbacks, Integer>{

	@Query("From Feedbacks fb where fb.feedbackId.users.id = :userId And fb.feedbackId.orders.id = :orderId And fb.feedbackId.products.id = :productId")
	Feedbacks findFeedbackByOrderIdAndUserIdAndProductId(@Param("userId") Integer userId, @Param("orderId") Integer orderId, @Param("productId") Integer productId);

	@Query("From Feedbacks fb where fb.feedbackId.products.id = :id")
	Page<Feedbacks> findFeedbackByProductId(@Param("productId") Integer id, Pageable pageable);

}
