package com.ubi.dbp.loginregistration.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Getter
@Configuration
@Component
public class URLConfiguration {

    @Value("${customer.baseUrl}")
    private String customerBaseURL;
    @Value("${customer.paths.custInqByMob}")
    private String custInqByMob;
    @Value("${customer.paths.getCustomerAcctsByCIF}")
    private String getCustomerAcctsByCIF;
    @Value("${customer.accountStatus}")
    private String acctStatus;
    @Value("${config.service.configBaseURL}")
    private String configServiceBaseURL;
    @Value("${config.service.fetchBusinessRules}")
    private String fetchBusinessRules;
    @Value("${customer.paths.getCustomerByCIF}")
    private String getCustomerByCIF;
    @Value("${aadhaar.proxy.baseUrl}")
    private String aadhaarProxyBaseUrl;
    @Value("${aadhaar.proxy.paths.generateOtp}")
    private String generateOtp;
    @Value("${aadhaar.proxy.paths.validateOtp}")
    private String validateOtp;
    @Value("${atmSwitch.proxy.baseUrl}")
    private String atmBaseURL;
    @Value("${atmSwitch.proxy.paths.verifyDebit}")
    private String verifyDebit;
    @Value("${verify.proxy.paths.checkMbRegistration}")
    private String checkMbRegistration;
    @Value("${verify.proxy.paths.securityQuestions}")
    private String securityQuestions;
    @Value("${verify.proxy.baseUrl}")
    private String baseUrl;
    @Value("${verify.proxy.paths.corpUserLogin}")
    private String corpUserLogin;
    @Value("${verify.proxy.paths.generateOtpEndpoint}")
    private String generateOtpEndpoint;
    @Value("${verify.proxy.paths.verifyOtpEndpoint}")
    private String verifyOtpEndpoint;
    @Value("${verify.proxy.paths.knowledgeQuestions}")
    private String knowledgeQuestions;
    @Value("${verify.proxy.paths.verifyOldLoginMpinEndpoint}")
    private String verifyOldLoginMpinEndpoint;
    @Value("${verify.proxy.paths.verifyTpinEndpoint}")
    private String verifyTpinEndpoint;

}
