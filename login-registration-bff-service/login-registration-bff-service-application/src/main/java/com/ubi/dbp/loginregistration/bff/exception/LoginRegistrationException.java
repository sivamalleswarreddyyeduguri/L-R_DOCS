package com.ubi.dbp.loginregistration.bff.exception;


import dbp.framework.common.domain.exception.DomainException;

public class LoginRegistrationException extends DomainException {

    private static final long serialVersionUID = -8004702236911760231L;
    
    public LoginRegistrationException(ErrorEnum error) {
        super(error.getCode(),error.getMessage());
      
    }
    public LoginRegistrationException(ErrorEnum error, Object... values) {
        super(error.getCode(),
                String.format(error.getMessage(),values));
    }
    
    public LoginRegistrationException(String error) {
        super(error, error);
    }
 
} 
