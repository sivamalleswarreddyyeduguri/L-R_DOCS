package com.ubi.dbp.loginregistration.service;


import com.ubi.dbp.loginregistration.model.*;

import reactor.core.publisher.Mono;


public interface LoginRegistrationService {
   Mono<GenericResponse> mbRegistration(MbRegistrationReq userReq);

   Mono<GenericResponse> setSecurityQuestion(SetSecurityQuestion setSecurityQuestion);

   Mono<GenericResponse> setPassword(SetPasswordReq setPasswordReq);

   public Mono<GenericResponse> setMpin(SetMpinReq setMpinReq);
   public Mono<GenericResponse> setTPin(SetTPinReq setTPinReq);

}
