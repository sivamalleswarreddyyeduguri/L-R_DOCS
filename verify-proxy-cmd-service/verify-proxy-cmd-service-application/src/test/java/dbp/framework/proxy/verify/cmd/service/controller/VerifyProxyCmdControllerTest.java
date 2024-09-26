//package dbp.framework.proxy.verify.cmd.service.controller;
//
//import dbp.framework.proxy.verify.cmd.service.exception.CustomException;
//import dbp.framework.proxy.verify.cmd.service.model.GenericResponse;
//import dbp.framework.proxy.verify.cmd.service.model.Response;
//import dbp.framework.proxy.verify.cmd.service.model.SetMpinReq;
//import dbp.framework.proxy.verify.cmd.service.service.VerifyProxyCmdService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Mono;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//
//@WebFluxTest(VerifyProxyCmdController.class)
//public class VerifyProxyCmdControllerTest {
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @MockBean
//    private VerifyProxyCmdService verifyProxyCmdService;
//
//    @Test
//    public void testSetMpinSuccess() {
//        // Prepare test data
//        SetMpinReq request = new SetMpinReq("12345", "1234", "1234", 1, 4, "registration");
//        GenericResponse<String> response = new GenericResponse<>(new Response("Success"), "00", "Data");
//
//        // Mock the service method
//        when(verifyProxyCmdService.setMpin(any(SetMpinReq.class))).thenReturn(Mono.just(response));
//
//        // Perform the test
//        webTestClient.post()
//                .uri("/verify-proxy/api/v1/mpin")
//                .contentType(APPLICATION_JSON)
//                .bodyValue(request)
//                .exchange()
//                .expectStatus().isEqualTo(HttpStatus.OK)
//                .expectHeader().contentType(APPLICATION_JSON)
//                .expectBody(GenericResponse.class)
//                .consumeWith(result -> {
//                    GenericResponse<String> responseBody = result.getResponseBody();
//                    assert responseBody != null;
//                    assertEquals(new Response("Success"), responseBody.getResponse());
//                    assertEquals("00", responseBody.getRespcode());
//                    assertEquals("Data", responseBody.getData());
//                }); 
//    }
//
//    @Test
//    public void testSetMpinNotFound() {
//        // Prepare test data
//        SetMpinReq request = new SetMpinReq("12345", "1234", "1234", 1, 4, "registration");
//        GenericResponse errorResponse = new GenericResponse<>(new Response("Not Found"), "404", null);
//
//        // Mock the service method to return an error
//        when(verifyProxyCmdService.setMpin(any(SetMpinReq.class))).thenReturn(Mono.error(new CustomException(errorResponse)));
//
//        // Perform the test
//        webTestClient.post()
//                .uri("/verify-proxy/api/v1/mpin")
//                .contentType(APPLICATION_JSON)
//                .bodyValue(request)
//                .exchange()
//                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
//                .expectHeader().contentType(APPLICATION_JSON);
//    }
//
//    @Test
//    public void testSetMpinInternalServerError() {
//        // Prepare test data
//        SetMpinReq request = new SetMpinReq("12345", "1234", "1234", 1, 4, "registration");
//        GenericResponse errorResponse = new GenericResponse<>(new Response("Error"), "500", null);
//
//        // Mock the service method to return an error
//        when(verifyProxyCmdService.setMpin(any(SetMpinReq.class))).thenReturn(Mono.error(new CustomException(errorResponse)));
//
//        // Perform the test
//        webTestClient.post()
//                .uri("/verify-proxy/api/v1/mpin")
//                .contentType(APPLICATION_JSON)
//                .bodyValue(request)
//                .exchange()
//                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
//                .expectHeader().contentType(APPLICATION_JSON);
//    }
//}
