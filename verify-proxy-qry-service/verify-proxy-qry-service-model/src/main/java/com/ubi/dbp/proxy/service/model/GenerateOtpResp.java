package com.ubi.dbp.proxy.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateOtpResp {
    private ResponseMessage response;
    private String respcode;
}
	


