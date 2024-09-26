package com.ubi.dbp.loginregistration.service;

import com.ubi.dbp.loginregistration.model.*;
import reactor.core.publisher.Mono;

public interface IbmVerifyService {

    Mono<GenerateOtpResp> generateOtp(GenerateOtpReq generateOtpReq);

    Mono<VerifyOtpResp> verifyOtp(VerifyOtpReq verifyOtpReq);

    Mono<SecurityQuestions> getQuestionsByCif(GetSecurityQuestionReq securityQuestionReq);

    Mono<CorpLoginResp> corpLogin(CoprpLoginReq coprpLoginReq);

    Mono<KnowledgeQuestionsResp> getAllKnowledgeQuestions();

    Mono<GenericResponse> verifyOldloginMpin(VerifyOldLoginMPin verifyOldLoginMPin);

    Mono<CheckMbRegistrationResp> checkMbRegistration(CheckMbRegistrationReq checkMbRegistrationReq);

    Mono<GenericResponse> verifyTpin(VerifyTpin verifyTpin);
}
