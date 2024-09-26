package com.ubi.dbp.proxy.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyTpin {

    private String cif;
    private String tpin;
    private String refreshtoken;
    private Integer allowedfailure;
}