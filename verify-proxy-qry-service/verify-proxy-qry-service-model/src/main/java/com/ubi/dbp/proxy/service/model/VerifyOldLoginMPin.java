package com.ubi.dbp.proxy.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyOldLoginMPin {

    private String cif;
    private String oldloginpin;
    private String event;
}