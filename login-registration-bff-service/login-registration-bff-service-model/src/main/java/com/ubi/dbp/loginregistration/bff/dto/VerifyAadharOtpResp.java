package com.ubi.dbp.loginregistration.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyAadharOtpResp{
	
	private String respCode;
	private String respDesc;
	
} 
