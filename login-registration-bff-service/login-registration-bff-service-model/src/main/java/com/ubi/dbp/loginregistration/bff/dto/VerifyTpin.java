package com.ubi.dbp.loginregistration.bff.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyTpin {
    @NotBlank(message = "cif must not be empty")
    private String cif;
    @NotBlank(message = "tpin must not be empty")
    private String tpin;
    @NotBlank(message = "refreshtoken must not be empty")
    private String refreshtoken;
    @NotNull(message = "allowedfailure must not be null")
    private Integer allowedfailure;
}