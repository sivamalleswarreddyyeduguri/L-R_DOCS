package dbp.framework.proxy.verify.qry.service.service;


import com.ubi.dbp.proxy.service.model.*;

import reactor.core.publisher.Mono;

public interface VerifyProxyQryService {
    Mono<GenerateOtpResp> generateOtp(GenerateOtpReq generateOtpReq);

    Mono<VerifyOtpResp> verifyOtp(VerifyOtpReq verifyOtpReq);

    Mono<CorpLoginResp> corpLogin(CorpLoginReq corpLoginReq);

    Mono<SecurityQuestions> getQuestionByCif(SecurityQuestionReq securityQuestionReq);

    Mono<KnowledgeQuestionsResp> getAllKnowledgeQuestions();

    Mono<ValidateKnowledgeQuestionsResp> validateUserKnowledgeQuestions(ValidateKnowledgeQuestionsReq validateKnowledgeQuestionsReq);

    Mono<GenericResponse> verifyOLdLoginMPin(VerifyOldLoginMPin verifyOldLoginMPin);

    Mono<CheckMbRegistrationResp> checkMbRegistration(CheckMbRegistrationReq checkMbRegistrationReq);

    Mono<GenericResponse> verifyTpin(VerifyTpin verifyTpin);
}
