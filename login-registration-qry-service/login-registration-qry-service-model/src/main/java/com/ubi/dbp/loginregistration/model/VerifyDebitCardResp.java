package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyDebitCardResp {

	 private String transRefNum;
	 private boolean success;
	 private String message;
	 
}
