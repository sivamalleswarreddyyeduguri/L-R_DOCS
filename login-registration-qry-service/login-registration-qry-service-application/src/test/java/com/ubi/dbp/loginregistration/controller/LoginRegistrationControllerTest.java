package com.ubi.dbp.loginregistration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.ubi.dbp.loginregistration.model.CheckMbRegistrationReq;
import com.ubi.dbp.loginregistration.model.CustomerByCifReq;
import com.ubi.dbp.loginregistration.model.VerifyMobileResp;
import com.ubi.dbp.loginregistration.service.LoginRegistrationService;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LoginRegistrationControllerTest {

	@InjectMocks
	LoginRegistrationController loginRegistrationController;
	
	@Mock
	LoginRegistrationService loginRegistrationService;
	
	private WebTestClient webTestClient;

	@BeforeEach
    public void setUp() {
        webTestClient = WebTestClient.bindToController(loginRegistrationController).build();
    }
		
	
	@Test
	public void testCheckExistingUser() {
		
		CheckMbRegistrationReq checkMbRegistrationReq = new CheckMbRegistrationReq();
		checkMbRegistrationReq.setDeviceid("49015420323452");
		checkMbRegistrationReq.setMobile("9082920587");
		checkMbRegistrationReq.setSimdata("12115|2|33");
			
		
		VerifyMobileResp verifyMobileResp = new VerifyMobileResp();
		List<CustomerByCifReq> customerByCifReqs = new ArrayList<>();
		customerByCifReqs.add(new CustomerByCifReq("634740930"));
		verifyMobileResp.setCustomerByCifReq(customerByCifReqs);
		 
		
		 when(loginRegistrationService.checkExistingUser(checkMbRegistrationReq)).thenReturn(Mono.just(verifyMobileResp));
		 
		  Mono<VerifyMobileResp> actual = loginRegistrationController.checkExistingUser(checkMbRegistrationReq);
		 
		 verify(loginRegistrationService).checkExistingUser(checkMbRegistrationReq);
		 
		 assertEquals(verifyMobileResp, actual.block());
		
		 
	}
}
