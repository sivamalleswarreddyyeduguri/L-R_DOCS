package com.ubi.dbp.loginregistration.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyOtpResp {

    private Response response;
    private String respcode; 
}
