package com.ubi.dbp.loginregistration.bff.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateAadharOtp {

	 @NotBlank(message = "cif must not be blank")
	 private String cif;
	 @NotBlank(message = "Aadhaar ID must not be blank")
	 @Pattern(regexp = "\\d{12}", message = "Aadhaar ID must be 12 digits")
	 private String vid;
	 
}
