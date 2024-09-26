package com.ubi.dbp.loginregistration.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetPasswordReq {

    private String cif;
    private String password;
    private String confirmpassword;
}