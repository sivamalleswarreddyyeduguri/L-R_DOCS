package com.ubi.dbp.loginregistration.bff.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GenerateOtpReq {

		@NotBlank(message = "cif must not be empty")
	    private String cif;
		@Email(message = "enter valid email")
	    private String mail;
		@NotBlank(message = "cif must not be empty")
		private String mobile;
	    
	}



