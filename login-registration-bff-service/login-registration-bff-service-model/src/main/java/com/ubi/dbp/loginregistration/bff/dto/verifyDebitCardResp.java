package com.ubi.dbp.loginregistration.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class verifyDebitCardResp {
	
    private String transRefNum;
    private boolean success;
    private String message;
}
