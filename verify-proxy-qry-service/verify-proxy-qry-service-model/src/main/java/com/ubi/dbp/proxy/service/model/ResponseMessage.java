package com.ubi.dbp.proxy.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public  class ResponseMessage {	
    private String mechanism;
    private String stateId;
    private String location;
    private String hint;
    private String sentTo;
    private String mappingRuleData;
    
}