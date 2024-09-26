package com.ubi.dbp.loginregistration.bff.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyOldLoginMPin {
    @NotBlank(message = "cif must not be empty")
    private String cif;
    @NotBlank(message = "oldloginpin must not be empty")
    private String oldloginpin;
    @NotBlank(message = "event must not be empty")
    private String event;
}