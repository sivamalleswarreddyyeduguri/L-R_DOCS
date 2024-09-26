package com.ubi.dbp.loginregistration.bff.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Configuration
@Getter
@Component
public class LoginRegistrationQryConfig {
		
	@Value("${loginRegistration.query.service.base.url}")
	private String loginRegistrationQrySvcBaseUrl;
	@Value("${loginRegistration.query.service.paths.verifyMobile}")
	private String verifyMobile;	
	@Value("${loginRegistration.query.service.paths.generateOtp}")
	private String generateOtp;
	@Value("${loginRegistration.query.service.paths.validateOtp}")
	private String validateOtp;
	@Value("${loginRegistration.query.service.paths.verifyDebitCard}")
	private String verifyDebCrd;
	@Value("${loginRegistration.query.service.paths.securityQuestions}")
	private String securityQuestions;
	@Value("${loginRegistration.query.service.paths.corpLogin}")
	private String corpUserLogin;
    @Value("${loginRegistration.query.service.paths.verifyTpinOtp}")
    private String verifyTpinOtp;
    @Value("${loginRegistration.query.service.paths.generateTpinOtp}")
    private String generateTpinOtp;
	@Value("${loginRegistration.query.service.paths.knowledgeQuestions}")
	private String knowledgeQuestions;
	@Value("${loginRegistration.query.service.paths.verifyOldLoginMpinEndpoint}")
	private String verifyOldLoginMpinEndpoint;
	@Value("${loginRegistration.query.service.paths.verifyTpinEndpoint}")
	private String verifyTpinEndpoint;
	@Value("${loginRegistration.query.service.paths.checkMbRegistration}")
	private String checkMbRegistration;

}
    