package com.ubi.dbp.loginregistration.bff.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class verifyDebitCard {

    private String transRefNum;
    @NotBlank(message = "Please provide card number")
    private String cardNum;
    @NotBlank(message = "Please provide card expiry number")
    private String cardExpiry;
    @NotBlank(message = "Please provide debit card pin")
    private String pin;
    
}
