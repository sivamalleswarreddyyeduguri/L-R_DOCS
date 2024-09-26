package com.ubi.dbp.loginregistration.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MbRegistrationReq {


    private String deviceid;
    private String mobile;
    private String cif;
    private String simdata;
    private String mail;


}
