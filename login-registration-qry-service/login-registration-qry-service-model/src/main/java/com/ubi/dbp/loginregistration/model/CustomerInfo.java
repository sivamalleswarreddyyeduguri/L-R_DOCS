package com.ubi.dbp.loginregistration.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfo {
		
	private String cif;
    private String acctNum;
    private String customerName;
    private String acctName;
    private Double depositAmount;
    private Double interestRate;
    private Double maturityAmount;
    private List<AcctBalance> acctBals;
    private String maturityDate;
    private String openingDate;
    private String operatingInstruction;
    private String schemeCode;
    private String schemeName;
    private String schemeType;
    private String acctStatus;
    private String acctType;
    private String subscriptionCode;
    private Boolean activeFlag;
    private String repaymentAccount;
}




//package com.ubi.dbp.loginregistration.model;
//
//import java.util.List;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class CustomerInfo {
//		
//	private String cif;
//    private String acctNum;
//    private String customerName;
//    private String acctName;
//    private Double depositAmount;
//    private Double interestRate;
//    private Double maturityAmount;
//    private List<AcctBalance> acctBals;
//    private String maturityDate;
//    private String openingDate;
//    private String operatingInstruction;
//    private List<Account> accounts;
//    private String subscriptionCode;
//    private Boolean activeFlag;
//    private RelatedParty repaymentAccount;
//     
//    
//    public static class RelatedParty{
//    	private String cif;
//    	private List<Account> accounts;
//    }
//    
//
//}





