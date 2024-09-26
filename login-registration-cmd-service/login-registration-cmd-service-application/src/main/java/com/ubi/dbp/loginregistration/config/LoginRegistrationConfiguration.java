package com.ubi.dbp.loginregistration.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "verify.proxy")
@Component
public class LoginRegistrationConfiguration {

	private String baseUrl;
	private String setMpinEndpoint;
	private String mbRegistration;
	private String securityQuestions;
	private String password;
	private String setTpinEndpoint;
	private String verifyOldLoginMpinEndpoint;

}

