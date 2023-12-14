package com.project.stationeryStore.service;

import com.project.stationeryStore.domain.payload.EmailDetails;
import com.project.stationeryStore.domain.payload.request.EmailOrderPaymentToUserRequest;
import com.project.stationeryStore.domain.payload.request.EmailPaymentNotificationRequest;
import com.project.stationeryStore.domain.payload.request.EmailUpdateStatusRequest;

public interface EmailService {

	void sendSimpleMail(EmailDetails email) throws Exception;
	
	void sendMailUPpgradeRank(EmailPaymentNotificationRequest request);
	
	void sendEmailUpdateStatus(EmailUpdateStatusRequest request);
	
	void sendEmailOrderPaymentToUser(EmailOrderPaymentToUserRequest request);
}
