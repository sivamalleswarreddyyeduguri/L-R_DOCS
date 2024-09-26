package com.ubi.dbp.loginregistration.bff.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetTPinReq {

	@NotBlank(message = "cif must not be null")
    private String cif;
	@NotBlank(message = "tpin must not be null")
    private String tpin;
	@NotBlank(message = "confirmtpin must not be null")
    private String confirmtpin;
	@NotNull(message = "tpinhistorycount must not be null")
    private Integer tpinhistorycount;
	@NotNull(message = "tpinlength must not be null")
    private Integer tpinlength;
	@NotBlank(message = "event must not be null")
    private String event;
}