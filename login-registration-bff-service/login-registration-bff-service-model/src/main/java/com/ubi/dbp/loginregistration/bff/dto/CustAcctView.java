package com.ubi.dbp.loginregistration.bff.dto;

import lombok.Data;

@Data
public class CustAcctView {
		
	private String cif;
    private String acctNum;
    private String name;
    private String dob;
    private String email;
    private Boolean isMinor;
    private Boolean isSeniorCitizen;
    private Boolean isUBOIStaff;
    private Boolean isNri;
    private Scheme scheme;
    private BankInfo bankInfo;
}
