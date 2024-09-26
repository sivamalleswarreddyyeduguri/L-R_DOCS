package com.ubi.dbp.loginregistration.bff.controller;

import com.ubi.dbp.loginregistration.bff.dto.CheckMbRegistrationReq;
import com.ubi.dbp.loginregistration.bff.dto.VerifyMobileResp;
import com.ubi.dbp.loginregistration.bff.service.LoginRegistrationBFFQryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LoginRegistrationQryControllerTest {

    @InjectMocks
    private LoginRegistrationQryController loginRegistrationQryController;

    @Mock
    private LoginRegistrationBFFQryService loginRegistrationBFFQryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void checkExistingUser_shouldReturnValidResponse() {
        CheckMbRegistrationReq request = new CheckMbRegistrationReq("1234567890", "deviceId123", "simData123");
        VerifyMobileResp expectedResponse = new VerifyMobileResp(null, "200");

        when(loginRegistrationBFFQryService.checkExistingUser(any(CheckMbRegistrationReq.class)))
                .thenReturn(Mono.just(expectedResponse));

        Mono<VerifyMobileResp> responseMono = loginRegistrationQryController.checkExistingUser(request);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> "200".equals(response.getRespcode()))
                .verifyComplete();
    }

    @Test
    void checkExistingUser_shouldHandleEmptyResponse() {
        CheckMbRegistrationReq request = new CheckMbRegistrationReq("1234567890", "deviceId123", "simData123");

        when(loginRegistrationBFFQryService.checkExistingUser(any(CheckMbRegistrationReq.class)))
                .thenReturn(Mono.empty());

        Mono<VerifyMobileResp> responseMono = loginRegistrationQryController.checkExistingUser(request);

        StepVerifier.create(responseMono)
                .expectComplete()
                .verify();
    }

    @Test
    void checkExistingUser_shouldHandleException() {
        CheckMbRegistrationReq request = new CheckMbRegistrationReq("1234567890", "deviceId123", "simData123");

        when(loginRegistrationBFFQryService.checkExistingUser(any(CheckMbRegistrationReq.class)))
                .thenReturn(Mono.error(new RuntimeException("Internal Server Error")));

        Mono<VerifyMobileResp> responseMono = loginRegistrationQryController.checkExistingUser(request);

        StepVerifier.create(responseMono)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Internal Server Error"))
                .verify();
    }
}
