package com.ubi.dbp.loginregistration.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTypeResponse {
		
	private String subscriptionCode;
    private String schemeTypeCode;
    private String schemeSubTypeCode;
    private String schemeCode;
    private String parameterName;
    private String channel;
//    private LocalDateTime effectiveFromDate;
//    private LocalDateTime effectiveTillDate;
    private String equalUnit;
    private String equalValue;
    private String maxUnit;
    private String maxValue;
    private String minUnit;
    private String minValue;
    private String remarks;
}
