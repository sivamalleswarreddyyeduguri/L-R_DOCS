package com.ubi.dbp.loginregistration.bff.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class LoginRegistrationBFFCmdConfig {

    @Value("${loginRegistration.command.service.base.url}")
    public String loginRegistrationCmdSvcBaseUrl;
    @Value("${loginRegistration.command.service.path}")
    public String loginRegistrationCmdPath;
    @Value("${loginRegistration.command.service.settpin}")
    private String setTpin;
    @Value("${loginRegistration.command.service.setmpin}")
    private String setMpin;
    @Value("${loginRegistration.command.service.securityQuestions}")
    private String securityQuestions;
    @Value("${loginRegistration.command.service.password}")
    private String password;
    @Value("${loginRegistration.command.service.mbRegistration}")
    private String mbRegistration;

}
