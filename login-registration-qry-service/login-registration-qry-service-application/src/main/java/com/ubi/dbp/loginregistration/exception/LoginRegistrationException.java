package com.ubi.dbp.loginregistration.exception;


import org.springframework.http.HttpStatus;

import dbp.framework.common.domain.exception.DomainException;


public class LoginRegistrationException extends DomainException{

    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    public LoginRegistrationException(ErrorConst error) {
        super(error.getCode(), error.getMessage());
    }
    
    public LoginRegistrationException(ErrorConst error, HttpStatus status) {
        super(error.getCode(), error.getMessage());
        this.status = status;
    }
    
    public LoginRegistrationException(String code, String error) {
        super(code,error);
    }

    public LoginRegistrationException(ErrorConst error, Object... values) {
        super(error.getCode(), String.format(error.getMessage(), values));
    }

	public HttpStatus getStatus() {
		return status;
	}

    
}