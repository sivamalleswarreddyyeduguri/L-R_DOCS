package com.ubi.dbp.loginregistration.bff.service.impl;

import com.ubi.dbp.loginregistration.bff.config.LoginRegistrationQryConfig;
import com.ubi.dbp.loginregistration.bff.dto.CheckMbRegistrationReq;
import com.ubi.dbp.loginregistration.bff.dto.VerifyMobileResp;
import com.ubi.dbp.loginregistration.bff.service.AbsCommonService.CallType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class LoginRegistrationBFFQryServiceImplTest {

    @InjectMocks
    private LoginRegistrationBFFQryServiceImpl loginRegistrationBFFQryServiceImpl;

    @Mock
    private LoginRegistrationQryConfig loginRegistrationBFFQryConfig;

    @Mock
    private CommonService commonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl()).thenReturn("http://localhost:8087");
        when(loginRegistrationBFFQryConfig.getVerifyMobile()).thenReturn("/api/v1/verify-mobile");
    }

    @Test
    void checkExistingUser_shouldReturnValidResponse() {
    	
        CheckMbRegistrationReq request = new CheckMbRegistrationReq("1234567890", "deviceId123", "simData123");
        VerifyMobileResp expectedResponse = new VerifyMobileResp(null, "200");

        when(commonService.getData(
                eq(null),
                eq("http://localhost:8087/api/v1/verify-mobile"),
                eq(request),
                eq(VerifyMobileResp.class),
                eq(CallType.POST),
                eq(false)))
            .thenReturn(expectedResponse);

       Mono<VerifyMobileResp> responseMono = loginRegistrationBFFQryServiceImpl.checkExistingUser(request);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> "200".equals(response.getRespcode()))
                .verifyComplete();
    }
 
    @Test
    void checkExistingUser_shouldHandleEmptyResponse() {
    	
        CheckMbRegistrationReq request = new CheckMbRegistrationReq("1234567890", "deviceId123", "simData123");

        when(commonService.getData(
                eq(null),
                eq("http://localhost:8087/api/v1/verify-mobile"),
                eq(request),
                eq(VerifyMobileResp.class),
                eq(CallType.POST),
                eq(false)))
            .thenReturn(null);
 
    
        Mono<VerifyMobileResp> responseMono = loginRegistrationBFFQryServiceImpl.checkExistingUser(request);

        StepVerifier.create(responseMono)
                .expectComplete()
                .verify();
    }

    @Test
    void checkExistingUser_shouldHandleException() {
       
        CheckMbRegistrationReq request = new CheckMbRegistrationReq("1234567890", "deviceId123", "simData123");

        when(commonService.getData(
                eq(null),
                eq("http://localhost:8087/api/v1/verify-mobile"),
                eq(request),
                eq(VerifyMobileResp.class),
                eq(CallType.POST),
                eq(false)))
            .thenThrow(new RuntimeException("Internal Server Error"));

       
        Mono<VerifyMobileResp> responseMono = loginRegistrationBFFQryServiceImpl.checkExistingUser(request);

       
        StepVerifier.create(responseMono)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Internal Server Error"))
                .verify();
    }
}
