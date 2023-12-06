package com.project.stationeryStore.service;

import java.util.List;

import com.project.stationeryStore.domain.dto.FeedbackDto;
import com.project.stationeryStore.domain.payload.request.FeedbackRequest;
import com.project.stationeryStore.domain.payload.request.RemoveFeedbackRequest;

public interface FeedbackService {
	
	List<FeedbackDto> createOrUpdateFeedback(FeedbackRequest request);
	
	Boolean removeFeedback(RemoveFeedbackRequest request);
	
	List<FeedbackDto> getFeedbackProduct(Integer productId);
}
