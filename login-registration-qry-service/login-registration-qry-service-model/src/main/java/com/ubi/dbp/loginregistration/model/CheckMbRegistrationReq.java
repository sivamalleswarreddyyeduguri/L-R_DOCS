package com.ubi.dbp.loginregistration.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckMbRegistrationReq {
		
	 @NotBlank(message = "mobile number must not be empty")
	 @Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid mobile number")	 
	 private String mobile;
	 @NotBlank(message = "deviceid must not be empty")
	 private String deviceid;
	 @NotBlank(message = "sim data must not be empty")
	 private String simdata;
	 
}
