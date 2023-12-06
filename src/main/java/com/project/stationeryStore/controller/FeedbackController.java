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
import com.project.stationeryStore.domain.dto.FeedbackDto;
import com.project.stationeryStore.domain.payload.request.FeedbackRequest;
import com.project.stationeryStore.domain.payload.request.RemoveFeedbackRequest;
import com.project.stationeryStore.domain.payload.response.ApiResponse;
import com.project.stationeryStore.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired 
	FeedbackService feedbackService;
	
	@PostMapping("/createOrUpdate")
	public ResponseEntity<ApiResponse> saveFeedback(@RequestBody FeedbackRequest request){
		final List<FeedbackDto> result = feedbackService.createOrUpdateFeedback(request);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, result, Constants.CREATE_SUCCESS_MSG));
	}
	@GetMapping("/{productId}")
	private ResponseEntity<ApiResponse> paginationFeedback(@PathVariable Integer productId){
		final List<FeedbackDto> result = feedbackService.getFeedbackProduct(productId);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,result,Constants.GET_SUCESS_MSG));
	}
	@PostMapping("/removeFeedback")
	public ResponseEntity<ApiResponse> removeFeedback(@RequestBody RemoveFeedbackRequest request){
		final Boolean result = feedbackService.removeFeedback(request);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, result, Constants.DELETE_SUCCESS_MSG));
	}
}
