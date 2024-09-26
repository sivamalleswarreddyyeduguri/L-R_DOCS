package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetTPinReq {

    private String cif;
    private String tpin;
    private String confirmtpin;
    private Integer tpinhistorycount;
    private Integer tpinlength;
    private String event;
}