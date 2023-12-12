package com.project.stationeryStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.stationeryStore.common.Constants;
import com.project.stationeryStore.domain.payload.request.LoginRequest;
import com.project.stationeryStore.domain.payload.request.SignupRequest;
import com.project.stationeryStore.domain.payload.request.TokenRefreshRequest;
import com.project.stationeryStore.domain.payload.response.ApiResponse;
import com.project.stationeryStore.domain.payload.response.JwtResponse;
import com.project.stationeryStore.domain.payload.response.TokenRefreshResponse;
import com.project.stationeryStore.domain.payload.response.UserResponse;
import com.project.stationeryStore.service.UserServices;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserServices userService;

	@PostMapping("/auth/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		final JwtResponse result = userService.signIn(loginRequest);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@PostMapping("/auth/refreshtoken")
	public ResponseEntity<TokenRefreshResponse> refreshtoken(@RequestBody TokenRefreshRequest request) {
		final TokenRefreshResponse result = userService.getRefreshtoken(request);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@PostMapping("/auth/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		final ApiResponse result = userService.signUp(signUpRequest);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@GetMapping()
	public ResponseEntity<ApiResponse> getAllUser() {
		final List<UserResponse> result = userService.getAllUser();
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, result, Constants.GET_SUCESS_MSG));
	}
	
}
