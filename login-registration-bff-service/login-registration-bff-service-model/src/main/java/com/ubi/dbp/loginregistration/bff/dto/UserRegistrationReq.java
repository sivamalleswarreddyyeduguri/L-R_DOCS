package com.ubi.dbp.loginregistration.bff.dto;


import com.ubi.dbp.loginregistration.bff.constants.LoginRegistrationConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationReq {
	
	@NotBlank(message = LoginRegistrationConstants.OPERATION_MESSAGE)
    private String operation;
    @Valid
    private GenerateAadharOtp generateAadharOtp;
    @Valid
    private VerifyAadharOtp verifyAadharOtp;
    @Valid
    private verifyDebitCard verifyDebitCard;
}

