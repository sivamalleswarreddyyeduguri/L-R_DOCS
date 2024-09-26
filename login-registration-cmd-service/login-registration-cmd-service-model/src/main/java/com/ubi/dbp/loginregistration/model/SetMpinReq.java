package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetMpinReq {

    private String cif;
    private String loginpin;
    private String confirmloginpin;
    private int loginpinhistorycount;
    private int loginpinlength;
    private String event;
}

