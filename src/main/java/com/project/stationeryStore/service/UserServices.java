package com.project.stationeryStore.service;

import java.util.List;

import com.project.stationeryStore.domain.payload.request.ChangePasswordRequest;
import com.project.stationeryStore.domain.payload.request.LoginRequest;
import com.project.stationeryStore.domain.payload.request.SignupRequest;
import com.project.stationeryStore.domain.payload.request.TokenRefreshRequest;
import com.project.stationeryStore.domain.payload.response.ApiResponse;
import com.project.stationeryStore.domain.payload.response.JwtResponse;
import com.project.stationeryStore.domain.payload.response.TokenRefreshResponse;
import com.project.stationeryStore.domain.payload.response.UserResponse;

public interface UserServices {

	 JwtResponse signIn(LoginRequest loginRequest);
	
	 TokenRefreshResponse getRefreshtoken(TokenRefreshRequest request);
	
	 ApiResponse signUp(SignupRequest signUpRequest);
	
	 ApiResponse changePassword(ChangePasswordRequest changePasswordRequest);
	
	 List<UserResponse> getAllUser();
	
}
