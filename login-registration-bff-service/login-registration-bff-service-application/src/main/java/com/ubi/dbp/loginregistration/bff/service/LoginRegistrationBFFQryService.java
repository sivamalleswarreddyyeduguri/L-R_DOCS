package com.ubi.dbp.loginregistration.bff.service;


import com.ubi.dbp.loginregistration.bff.dto.*;

import reactor.core.publisher.Mono;

public interface LoginRegistrationBFFQryService {

    Mono<VerifyMobileResp> checkExistingUser(CheckMbRegistrationReq mobile);

    Mono<UserRegistrationResp> verifyUserRegistrationDetails(UserRegistrationReq aadharCardReq);

    Mono<GetSecurityQuestionsResp> getSecurityQuestions(GetSecurityQuestionReq securityQuestionReq);

    Mono<CorpLoginResp> corpLogin(CoprpLoginReq coprpLoginReq);

    Mono<GenerateOtpResp> generateTpinOtp(GenerateOtpReq generateOtpReq);

    Mono<VerifyOtpResp> verifyTpinOtp(VerifyOtpReq verifyOtpReq);

    Mono<KnowledgeQuestionsResp> getAllKnowledgeQuestions();

    Mono<GenericResponse> verifyOldloginMpin(VerifyOldLoginMPin verifyOldLoginMPin);

    Mono<CheckMbRegistrationResp> checkMbRegistration(CheckMbRegistrationReq checkMbRegistrationReq);

    Mono<GenericResponse> verifyTpin(VerifyTpin verifyTpin);
}
 