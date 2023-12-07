package com.project.stationeryStore.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.stationeryStore.domain.dto.FeedbackDto;
import com.project.stationeryStore.domain.inventory.FeedbackId;
import com.project.stationeryStore.domain.inventory.Feedbacks;
import com.project.stationeryStore.domain.inventory.Orders;
import com.project.stationeryStore.domain.inventory.Products;
import com.project.stationeryStore.domain.inventory.Users;
import com.project.stationeryStore.domain.payload.request.FeedbackRequest;
import com.project.stationeryStore.domain.payload.request.RemoveFeedbackRequest;
import com.project.stationeryStore.repository.FeedbackRepository;
import com.project.stationeryStore.repository.OrderRepository;
import com.project.stationeryStore.repository.ProductRepository;
import com.project.stationeryStore.repository.UserRepository;
import com.project.stationeryStore.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	
	@Override
	public List<FeedbackDto> createOrUpdateFeedback(FeedbackRequest request) {
		
		List<FeedbackDto> listFeedback = new ArrayList<FeedbackDto>();
		List<FeedbackId> listFeedbackIds = new ArrayList<FeedbackId>();
		FeedbackId feedbackId = new FeedbackId();
		Feedbacks feedback = null;
		
		feedback = feedbackRepository.findFeedbackByOrderIdAndUserIdAndProductId(request.getUserId(),request.getOrderId(),request.getProductId());
	
		if(feedback == null) {
			feedback = new Feedbacks();
			feedback.setCreatedDate(new Date());
			Users user = userRepository.findUserByIdAndIsActive(request.getUserId());
			Products product = productRepository.findByProductById(request.getProductId());
			Orders order = orderRepository.findOrderByIdAndIsActive(request.getOrderId());
			feedbackId.setOrders(order);
			feedbackId.setProducts(product);
			feedbackId.setUsers(user);
			listFeedbackIds.add(feedbackId);
			feedback.setFeedbackId(feedbackId);
		}
		else {
			feedback.setUpdatedDate(new Date());
			feedback.setAdminReply(request.getReplyAdmin());
			feedback.setUpdatedDate(new Date());
			feedback.setReplyDate(new Date());
		}
		feedback.setIsActive(true);
		feedback.setComments(request.getComment());
	
		feedbackRepository.save(feedback);
		
		listFeedback = getFeedbackProduct(request.getProductId());
		return listFeedback;
	}

	@Override
	public Boolean removeFeedback(RemoveFeedbackRequest request) {
		Boolean result = false;
		Feedbacks feedback = feedbackRepository.findFeedbackByOrderIdAndUserIdAndProductId(request.getUserId(), request.getOrderId(), request.getProductId());
		if(feedback != null) {
			feedbackRepository.delete(feedback);
			result = true;
		}
		return result;
	}

	@Override
	public List<FeedbackDto> getFeedbackProduct(Integer productId) {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("createdDate"));
		Page<Feedbacks> listFeedback = feedbackRepository.findFeedbackByProductId(productId, pageable);
		FeedbackDto dto = null;
		List<FeedbackDto> listFeedbackDto = new ArrayList<FeedbackDto>();
		for(Feedbacks feedback : listFeedback) {
			dto = new FeedbackDto();
			dto.setComment(feedback.getComments());
			dto.setCreateAt(feedback.getCreatedDate());
			dto.setIsActive(feedback.getIsActive());
			dto.setOrderId(feedback.getFeedbackId().getOrders().getId());
			dto.setProductId(feedback.getFeedbackId().getProducts().getId());
			dto.setUserId(feedback.getFeedbackId().getUsers().getId());
			dto.setRelyData(feedback.getReplyDate());
			dto.setReplyAdmin(feedback.getAdminReply());
			dto.setUpdateDate(feedback.getUpdatedDate());
			listFeedbackDto.add(dto);
		}
		return listFeedbackDto;
	}

}
