package com.ubi.dbp.loginregistration.exception;

import lombok.Getter;

@Getter
public enum Error {

	UNABLE_TO_PROCESS("UNABLE_TO_PROCESS", "Unable to Process your request"),
	RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "Requested resource not found"),
	PROCESSING_FAILED("PROCESSING_FAILED", "Request processing failed"),
	NO_RECORD_FOUND("NO_RECORD_FOUND", "No records found for given input"),
	;

	private final String code;
	private final String message;

	Error(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + message;
	}

}
