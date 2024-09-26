package com.ubi.dbp.loginregistration.bff.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TAndCRequest {
	@Schema(required = true, description = "cif")
	private String cif;
	@Schema(required = true, description = "tandCType")
	private String tandCType;
	
}