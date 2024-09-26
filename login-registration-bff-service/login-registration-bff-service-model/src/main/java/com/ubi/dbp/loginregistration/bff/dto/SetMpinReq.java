package com.ubi.dbp.loginregistration.bff.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetMpinReq {
    @NotBlank(message = "cif must not be empty")
    private String cif;
    @NotBlank(message = "loginpin must not be empty")
    private String loginpin;
    @NotBlank(message = "confirmloginpin must not be empty")
    private String confirmloginpin;
    @NotNull(message = "loginhistorycount must not be empty")
    private Integer loginhistorycount;
    @NotNull(message = "loginpinlength must not be empty")
    private Integer loginpinlength;
    @NotBlank(message = "event must not be empty")
    private String event;


}
