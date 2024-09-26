package com.ubi.dbp.loginregistration.service;

import com.ubi.dbp.loginregistration.model.*;

import reactor.core.publisher.Mono;

public interface AadharService {
	 
	 Mono<GenerateAadharOtpResp>  generateAadharOtp(GenerateAadharOtp otpReq);
	 
	 Mono<VerifyAadharOtpResp> verifyAadharOtp(VerifyAadharOtp verifyAadharOtpReq);

}
