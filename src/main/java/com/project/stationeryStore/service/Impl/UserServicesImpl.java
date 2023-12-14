package com.project.stationeryStore.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.stationeryStore.common.Constants;
import com.project.stationeryStore.domain.inventory.ERoles;
import com.project.stationeryStore.domain.inventory.RefreshToken;
import com.project.stationeryStore.domain.inventory.Roles;
import com.project.stationeryStore.domain.inventory.Users;
import com.project.stationeryStore.domain.payload.request.ChangePasswordRequest;
import com.project.stationeryStore.domain.payload.request.LoginRequest;
import com.project.stationeryStore.domain.payload.request.SignupRequest;
import com.project.stationeryStore.domain.payload.request.TokenRefreshRequest;
import com.project.stationeryStore.domain.payload.response.ApiResponse;
import com.project.stationeryStore.domain.payload.response.JwtResponse;
import com.project.stationeryStore.domain.payload.response.TokenRefreshResponse;
import com.project.stationeryStore.domain.payload.response.UserResponse;
import com.project.stationeryStore.repository.RolesRepository;
import com.project.stationeryStore.repository.UserRepository;
import com.project.stationeryStore.security.jwt.JwtUtils;
import com.project.stationeryStore.security.security.RefreshTokenService;
import com.project.stationeryStore.security.security.UserDetailsImpl;
import com.project.stationeryStore.service.FileService;
import com.project.stationeryStore.service.UserServices;

@Service
public class UserServicesImpl implements UserServices{

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	RolesRepository rolesRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	RefreshTokenService refreshTokenService;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	FileService storageService;
	
	@Override
	public JwtResponse signIn(LoginRequest loginRequest) {

		JwtResponse jwtResponse = new JwtResponse();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList()).get(0);
		String jwt = jwtUtils.generateJwtToken(authentication);
		RefreshToken refreshToken = refreshTokenService.getByUserId(userDetails.getId());
		if (refreshToken != null ) {
			refreshTokenService.deleteByUserId(userDetails.getId());
		}
		refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
		
		jwtResponse.setId(userDetails.getId());
		jwtResponse.setEmail(userDetails.getEmail());
		jwtResponse.setUsername(userDetails.getUsername());
		jwtResponse.setRefreshToken(refreshToken.getToken());
		
		jwtResponse.setToken(jwt);
		jwtResponse.setRoles(roles);
		return jwtResponse;
	}

	@Override
	public TokenRefreshResponse getRefreshtoken(TokenRefreshRequest request) {
		TokenRefreshResponse refreshResponse = new TokenRefreshResponse();
		String requestRefreshToken = request.getRefreshToken();
		RefreshToken refreshToken = refreshTokenService.getByToken(requestRefreshToken).orElse(null);
		if (refreshToken != null) {
			refreshToken = refreshTokenService.verifyExpiration(refreshToken);
			Users user = refreshToken.getUser();
			String token = jwtUtils.generateTokenFromUsername(user.getUsername());
			refreshResponse.setAccessToken(token);
			refreshResponse.setRefreshToken(refreshToken.getToken());
		}
		return refreshResponse;
	}

	@Override
	public ApiResponse signUp(SignupRequest signUpRequest) {
		Users user = new Users();
		ApiResponse response = new ApiResponse();
		user.setEmail(signUpRequest.getEmail());
		user.setAddress(signUpRequest.getAddress());
		user.setName(signUpRequest.getFirstName() + " " + signUpRequest.getLastName());
		user.setTelNum(signUpRequest.getPhone());
		user.setName(signUpRequest.getFirstName());
		user.setIsActive(Boolean.TRUE);
		user.setUsername(signUpRequest.getUserName());
		user.setPasswordSalt(encoder.encode(signUpRequest.getPassword()));
		String strRoles = signUpRequest.getRoles();
		Roles role = null;
		switch (strRoles) {
			case "admin":
				role = rolesRepository.findByName(ERoles.Role_Admin)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				break;
			case "mod":
				role = rolesRepository.findByName(ERoles.Role_User)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				break;
			default:
				role = rolesRepository.findByName(ERoles.Role_User)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		}
		user.setRole(role);
		userRepository.save(user);
		response.setData(user);
		response.setMessage(Constants.SIGNUP_SUCCESS_MSG);
		response.setSuccess(Boolean.TRUE);
		return response;
	}

	@Override
	public ApiResponse changePassword(ChangePasswordRequest changePasswordRequest) {
		ApiResponse response = new ApiResponse();
		String oldPassword = changePasswordRequest.getOldPassword();
		Users user = userRepository.findById(changePasswordRequest.getUserId()).orElse(null);
		if (encoder.matches(oldPassword, user.getPasswordSalt())) {
			user.setPasswordSalt(encoder.encode(changePasswordRequest.getNewPassword()));
			response.setSuccess(true);
			user = userRepository.save(user);
		} else {
			response.setSuccess(false);
			response.setMessage("Mật khẩu cũ không chính xác");
		}
		return response;
	}

	@Override
	public List<UserResponse> getAllUser() {
		return userRepository.findAllUsers();
	}
	
}
