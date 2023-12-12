package com.project.stationeryStore.domain.payload.response;

import java.util.Date;

public interface UserResponse {

	public Integer getUserId();
	
	public String getAddress();
	
	public String getPhone();
	
	public String getEmail();
	
	public String Name();
	
	public Boolean getActive();
	
	public Date getCreatedAt();
	
}
