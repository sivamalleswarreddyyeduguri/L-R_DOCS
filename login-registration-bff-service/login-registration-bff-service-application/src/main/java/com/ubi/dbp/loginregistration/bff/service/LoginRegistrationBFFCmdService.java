package com.ubi.dbp.loginregistration.bff.service;


import com.ubi.dbp.loginregistration.bff.dto.GenericResponse;
import com.ubi.dbp.loginregistration.bff.dto.MbRegistrationReq;
import com.ubi.dbp.loginregistration.bff.dto.SetPasswordReq;
import com.ubi.dbp.loginregistration.bff.dto.SetSecurityQuestion;
import com.ubi.dbp.loginregistration.bff.dto.*;
import reactor.core.publisher.Mono;

public interface LoginRegistrationBFFCmdService {


    Mono<GenericResponse> setSecurityQuestion(SetSecurityQuestion setSecurityQuestion);

    Mono<GenericResponse> setPassword(SetPasswordReq setPasswordReq);

    Mono<GenericResponse> mbRegistration(MbRegistrationReq mbRegistrationReq);

    public Mono<GenericResponse> setMpin(SetMpinReq setMpinReq);

    public Mono<GenericResponse> setTPin(SetTPinReq settpinreq);

}
