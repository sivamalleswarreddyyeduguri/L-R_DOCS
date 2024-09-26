package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTypeReq { 
	
	private String subscriptionCode;
	private String schemeTypeCode;
	private String schemeSubTypeCode;
	private String schemeCode;
	
	
}
