package com.ubi.dbp.loginregistration.bff.controller;

import com.ubi.dbp.loginregistration.bff.dto.FileData;
import com.ubi.dbp.loginregistration.bff.model.TAndCRequest;
import com.ubi.dbp.loginregistration.bff.service.LoginRegistrationBFFService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LoginRegistrationBFFControllerTest {

    @Mock
    private LoginRegistrationBFFService bffQryRequestService;

    @InjectMocks
    private LoginRegistrationBFFController loginRegistrationBFFController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTermsAndCondition_ValidRequest() {
        TAndCRequest request = new TAndCRequest("123456", "MODULE_NAME");
        FileData mockFileData = new FileData("MODULE_NAME_T&C.pdf", "MockBase64Data");
        when(bffQryRequestService.getTermsAndCondition(any(TAndCRequest.class)))
                .thenReturn(Mono.just(mockFileData));

        Mono<FileData> response = loginRegistrationBFFController.getTermsAndCondition(request);

        StepVerifier.create(response)
                .expectNextMatches(fileData -> fileData.getFileName().equals("MODULE_NAME_T&C.pdf")
                        && fileData.getData().equals("MockBase64Data"))
                .verifyComplete();
    }


    @Test
    void testGetTermsAndCondition_ServiceThrowsException() {
        TAndCRequest request = new TAndCRequest("123456", "MODULE_NAME");
        when(bffQryRequestService.getTermsAndCondition(any(TAndCRequest.class)))
                .thenReturn(Mono.error(new RuntimeException("Service exception")));

        Mono<FileData> response = loginRegistrationBFFController.getTermsAndCondition(request);

        StepVerifier.create(response)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Service exception"))
                .verify();
    }
}
