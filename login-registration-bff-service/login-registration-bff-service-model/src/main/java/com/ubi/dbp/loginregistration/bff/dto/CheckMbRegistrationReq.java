package com.ubi.dbp.loginregistration.bff.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckMbRegistrationReq {


    @NotBlank(message = "mobile number must not be empty")
    @Size(min = 10, max = 10, message = "mobile number must be exactly 10 digits")
    private String mobile;
    @NotBlank(message = "deviceid must not be empty")
    private String deviceid;
    @NotBlank(message = "sim data must not be empty")
    private String simdata;


}



