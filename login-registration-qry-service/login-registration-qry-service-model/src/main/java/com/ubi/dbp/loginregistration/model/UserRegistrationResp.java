package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationResp {
		
	private GenerateAadharOtpResp generateAadharOtpResp;
	private VerifyAadharOtpResp verifyAadharOtpResp;
	private DebitCardRegResp debitCardRegResp;
}
