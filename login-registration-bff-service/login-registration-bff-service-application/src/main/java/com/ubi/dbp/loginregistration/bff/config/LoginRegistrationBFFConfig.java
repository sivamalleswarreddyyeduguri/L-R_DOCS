package com.ubi.dbp.loginregistration.bff.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class LoginRegistrationBFFConfig {

    @Value("${loginRegistration.path.tnc}")
    private String termsAndCondition;
}
