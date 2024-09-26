package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyDebitCard {

	 private String transRefNum;
	 private String cardNum;
	 private String cardExpiry;
	 private String pin;
	 	
	 
}
