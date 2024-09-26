package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	private String message;
	private String code;
	}
