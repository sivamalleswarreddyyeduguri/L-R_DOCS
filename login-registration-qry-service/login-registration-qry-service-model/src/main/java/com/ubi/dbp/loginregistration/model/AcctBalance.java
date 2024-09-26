package com.ubi.dbp.loginregistration.model;

import lombok.Data;

@Data
public class AcctBalance {
	
	private String balTypeCode;
    private Double amount;
    private String currencyCode;
}
