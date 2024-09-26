//package dbp.framework.proxy.verify.cmd.service.service.impl;
//
//import dbp.framework.proxy.verify.cmd.service.config.VerifyProxyConfiguration;
//import dbp.framework.proxy.verify.cmd.service.exception.CustomException;
//import dbp.framework.proxy.verify.cmd.service.model.GenericResponse;
//import dbp.framework.proxy.verify.cmd.service.model.Response;
//import dbp.framework.proxy.verify.cmd.service.model.SetMpinReq;
//import dbp.framework.proxy.verify.cmd.service.model.VerifyErrorResponse;
//import dbp.framework.proxy.verify.cmd.service.service.VerifyProxyCmdService;
//import dbp.framework.proxy.verify.cmd.service.serviceImpl.VerifyProxyCmdServiceImpl;
//import dbp.framework.proxy.verify.cmd.service.util.AppUtils;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
//import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec;
//import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
//import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
//import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
//import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class VerifyProxyCmdServiceImplTest {
//
//    @Mock
//    private VerifyProxyConfiguration verifyProxyConfiguration;
//
//    @Mock
//    private WebClient.Builder webClientBuilder;
//
//    @Mock
//    private WebClient webClient;
//
//    @Mock
//    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
//
//    @Mock
//    private WebClient.RequestBodySpec requestBodySpec;
//
//    @Mock
//    private WebClient.RequestHeadersSpec requestHeadersSpec;
//
//    @Mock
//    private WebClient.ResponseSpec responseSpec;
//
//    @InjectMocks
//    private VerifyProxyCmdServiceImpl verifyProxyCmdServiceImpl;
//
//    private final String BASE_URL = "http://localhost:8080";
//    private final String SET_PIN_PATH = "/set-pin";
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        when(verifyProxyConfiguration.getBaseUrl()).thenReturn(BASE_URL);
//        when(verifyProxyConfiguration.getSetmpin()).thenReturn(SET_PIN_PATH);
//        when(webClientBuilder.build()).thenReturn(webClient);
//    }
//
//    @Test
//    public void testSetMpin_Success() {
//        SetMpinReq setMpinReq = new SetMpinReq();
//        GenericResponse expectedResponse = new GenericResponse();
//        expectedResponse.setResponse(new Response("Success"));
//
//        when(webClient.post()).thenReturn(requestBodyUriSpec);
//        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
//        when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
//        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.bodyToMono(GenericResponse.class)).thenReturn(Mono.just(expectedResponse));
//
//        Mono<GenericResponse> responseMono = verifyProxyCmdServiceImpl.setMpin(setMpinReq);
//
//        StepVerifier.create(responseMono)
//                .expectNextMatches(response -> response.getMessage().equals("Success"))
//                .verifyComplete();
//
//        verify(webClient).post();
//        verify(requestBodyUriSpec).uri(eq(BASE_URL + SET_PIN_PATH));
//        verify(requestBodySpec).bodyValue(eq(setMpinReq));
//        verify(requestHeadersSpec).retrieve();
//        verify(responseSpec).bodyToMono(GenericResponse.class);
//    }
//
//    @Test
//    public void testSetMpin_NotFound() {
//        SetMpinReq setMpinReq = new SetMpinReq();
//        VerifyErrorResponse errorResponse = new VerifyErrorResponse();
//        GenericResponse<VerifyErrorResponse> expectedResponse = new GenericResponse<>();
//        expectedResponse.setData(errorResponse);
//
//        when(webClient.post()).thenReturn(requestBodyUriSpec);
//        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
//        when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
//        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.onStatus(eq(HttpStatus.NOT_FOUND), any())).thenReturn(responseSpec);
//        when(responseSpec.bodyToMono(VerifyErrorResponse.class)).thenReturn(Mono.just(errorResponse));
//
//        Mono<GenericResponse> responseMono = verifyProxyCmdServiceImpl.setMpin(setMpinReq);
//
//        StepVerifier.create(responseMono)
//                .expectError(CustomException.class)
//                .verify();
//
//        verify(webClient).post();
//        verify(requestBodyUriSpec).uri(eq(BASE_URL + SET_PIN_PATH));
//        verify(requestBodySpec).bodyValue(eq(setMpinReq));
//        verify(requestHeadersSpec).retrieve();
//        verify(responseSpec).onStatus(eq(HttpStatus.NOT_FOUND), any());
//    }
//
//    @Test
//    public void testSetMpin_InternalServerError() {
//        SetMpinReq setMpinReq = new SetMpinReq();
//        VerifyErrorResponse errorResponse = new VerifyErrorResponse();
//        GenericResponse<VerifyErrorResponse> expectedResponse = new GenericResponse<>();
//        expectedResponse.setData(errorResponse);
//
//        when(webClient.post()).thenReturn(requestBodyUriSpec);
//        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
//        when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
//        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.onStatus(eq(HttpStatus.INTERNAL_SERVER_ERROR), any())).thenReturn(responseSpec);
//        when(responseSpec.bodyToMono(VerifyErrorResponse.class)).thenReturn(Mono.just(errorResponse));
//
//        Mono<GenericResponse> responseMono = verifyProxyCmdServiceImpl.setMpin(setMpinReq);
//
//        StepVerifier.create(responseMono)
//                .expectError(CustomException.class)
//                .verify();
//
//        verify(webClient).post();
//        verify(requestBodyUriSpec).uri(eq(BASE_URL + SET_PIN_PATH));
//        verify(requestBodySpec).bodyValue(eq(setMpinReq));
//        verify(requestHeadersSpec).retrieve();
//        verify(responseSpec).onStatus(eq(HttpStatus.INTERNAL_SERVER_ERROR), any());
//    }
//}
