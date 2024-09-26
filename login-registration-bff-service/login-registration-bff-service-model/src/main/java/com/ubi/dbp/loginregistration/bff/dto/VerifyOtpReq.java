package com.ubi.dbp.loginregistration.bff.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VerifyOtpReq {

	@NotBlank(message = "cif must not be empty")
	private String cif;
	@NotBlank(message = "otp must not be empty")
	private String otp;
	@NotBlank(message = "otphint must not be empty")
	private String otphint;
	@NotBlank(message = "id must not be empty")
	private String id;
	@NotBlank(message = "allowedfailure must not be empty")
	private String allowedfailure;
	@NotBlank(message = "blockinghours must not be empty")
	private String blockinghours;
	    
	}



