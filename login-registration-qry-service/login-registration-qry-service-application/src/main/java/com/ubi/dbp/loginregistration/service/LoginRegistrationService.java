 package com.ubi.dbp.loginregistration.service;

import com.ubi.dbp.loginregistration.model.*;
import org.springframework.web.bind.annotation.RequestBody;

import reactor.core.publisher.Mono;

public interface LoginRegistrationService {

	 	 
	 public Mono<VerifyMobileResp> checkExistingUser(@RequestBody CheckMbRegistrationReq mobile);



}
