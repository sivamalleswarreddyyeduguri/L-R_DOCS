package com.ubi.dbp.loginregistration.exception;


import dbp.framework.common.domain.exception.DomainException;

public class LoginRegistrationException extends DomainException{

    private static final long serialVersionUID = 1L;

    public LoginRegistrationException(Error error) {
        super(error.getCode(), error.getMessage());
    }
    public LoginRegistrationException(String code, String error) {
        super(code,error);
    }
    
    public LoginRegistrationException(Error error, Object... values) {
        super(error.getCode(), String.format(error.getMessage(), values));
    }

}