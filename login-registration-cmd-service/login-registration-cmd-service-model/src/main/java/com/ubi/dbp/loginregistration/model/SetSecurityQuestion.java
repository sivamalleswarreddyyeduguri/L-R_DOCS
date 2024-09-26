package com.ubi.dbp.loginregistration.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetSecurityQuestion {

    private String cif;
    private String deviceid;
    private String simdata;
    private List<Questions> questions;
}
