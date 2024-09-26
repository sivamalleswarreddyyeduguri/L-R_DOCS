package com.ubi.dbp.proxy.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GenerateOtpReq {

    private String cif;
    private String mail;
    private String mobile;

}



