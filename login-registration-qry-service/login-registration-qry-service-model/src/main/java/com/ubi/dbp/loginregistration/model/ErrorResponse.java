package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	private String errorId;
	private List<ErrorMessage> errors;
	}
