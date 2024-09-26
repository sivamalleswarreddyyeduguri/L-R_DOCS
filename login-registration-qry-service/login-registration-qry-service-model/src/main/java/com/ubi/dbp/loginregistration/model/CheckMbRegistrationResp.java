package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckMbRegistrationResp {
	
	private MbRegistrationResp response;
	private String respcode;
	
}

