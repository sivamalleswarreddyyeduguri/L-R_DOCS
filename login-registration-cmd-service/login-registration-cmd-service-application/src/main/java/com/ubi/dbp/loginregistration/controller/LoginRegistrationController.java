package com.ubi.dbp.loginregistration.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ubi.dbp.loginregistration.model.*;
import com.ubi.dbp.loginregistration.service.LoginRegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/login-registration/api/v1")
@AllArgsConstructor 
@Slf4j
public class LoginRegistrationController {
	
	private final LoginRegistrationService loginRegistrationService;

	    @PostMapping(value = "/mpin", produces = "application/json")
	    public Mono<GenericResponse> setMpin(@RequestBody SetMpinReq setMpinReq) {
			    return loginRegistrationService.setMpin(setMpinReq);
		}

	@PostMapping(value = "/register/user", produces = "application/json")
	public Mono<GenericResponse> mbRegistration(@RequestBody MbRegistrationReq mbRegistrationReq) {
		return loginRegistrationService.mbRegistration(mbRegistrationReq);
	}

	@PostMapping(value = "/security-question", produces = "application/json")
	public Mono<GenericResponse> setSecurityQuestion(@RequestBody SetSecurityQuestion setSecurityQuestion) {
		log.info("inside setSecurityQuestion()");
		return loginRegistrationService.setSecurityQuestion(setSecurityQuestion);
	}

	@PostMapping(value = "/password", produces = "application/json")
	public Mono<GenericResponse> setPassword(@RequestBody SetPasswordReq setPasswordReq) {
		return loginRegistrationService.setPassword(setPasswordReq);
	}

    @PostMapping(value = "/tpin", produces = "application/json")
	public Mono<GenericResponse> setTPin(@RequestBody SetTPinReq settpinreq){
		return loginRegistrationService.setTPin(settpinreq);
	}
}