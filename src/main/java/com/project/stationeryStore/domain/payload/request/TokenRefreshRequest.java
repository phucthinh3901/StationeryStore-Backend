package com.project.stationeryStore.domain.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TokenRefreshRequest {
	private String refreshToken;
}
