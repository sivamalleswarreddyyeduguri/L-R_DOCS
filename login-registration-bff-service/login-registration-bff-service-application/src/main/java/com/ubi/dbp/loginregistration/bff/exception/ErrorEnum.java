package com.ubi.dbp.loginregistration.bff.exception;

public enum ErrorEnum {

    CUST_NOT_FOUND("CUST_NOT_FOUND", "Customer not found."),
    ACCT_NOT_AVAILABLE("ACCT_NOT_AVAILABLE", "ACCOUNT is not available or invalid."),
    NO_LOGINREGISTRATION_TYPE("NO_LoginRegistration_TYPE","Type Should only be LoginRegistration"),
	INVALID_OPERATION_TYPE("INVALID_OPERATION_TYPE","invalid operation"),
    NO_VALID_EVENT_TYPE("NO_VALID_EVENT_TYPE","Event type should be valid")
    ;
    
    private final String code;
    private final String message;
    ErrorEnum(String code, String message) {
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
