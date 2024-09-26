package com.ubi.dbp.loginregistration.bff.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class UserRegistrationResp {
		
	private GenerateAadharOtpResp generateAadharOtpResp;
	private VerifyAadharOtpResp verifyAadharOtpResp;
	private verifyDebitCardResp debitCardRegResp;
	
}
