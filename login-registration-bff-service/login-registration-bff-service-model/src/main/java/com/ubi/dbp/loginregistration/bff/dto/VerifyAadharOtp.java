package com.ubi.dbp.loginregistration.bff.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyAadharOtp {

	@NotBlank(message = "Please enter otp")
	private String otp;
	@NotBlank(message = "please enter Aadhaar number")
	@Pattern(regexp = "\\d{12}", message = "Aadhaar ID must be 12 digits")
	private String uid;
	@NotBlank(message = "Please provide Transaction number")
	private String txn;	
	
}
