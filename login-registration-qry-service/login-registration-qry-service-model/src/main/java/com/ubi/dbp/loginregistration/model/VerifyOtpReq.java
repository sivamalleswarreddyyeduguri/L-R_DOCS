package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VerifyOtpReq {

    private String cif;
    private String otp;
    private String otphint;
    private String id;
    private String allowedfailure;
    private String blockinghours;

}



