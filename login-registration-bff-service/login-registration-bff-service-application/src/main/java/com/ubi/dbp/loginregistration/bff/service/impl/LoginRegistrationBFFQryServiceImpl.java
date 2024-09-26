package com.ubi.dbp.loginregistration.bff.service.impl;

import com.ubi.dbp.loginregistration.bff.dto.*;
import com.ubi.dbp.loginregistration.bff.enums.Event;
import org.springframework.stereotype.Service;
import com.ubi.dbp.loginregistration.bff.config.LoginRegistrationQryConfig;
import com.ubi.dbp.loginregistration.bff.constants.LoginRegistrationConstants;
import com.ubi.dbp.loginregistration.bff.exception.ErrorEnum;
import com.ubi.dbp.loginregistration.bff.exception.LoginRegistrationException;
import com.ubi.dbp.loginregistration.bff.service.AbsCommonService.CallType;
import com.ubi.dbp.loginregistration.bff.service.LoginRegistrationBFFQryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class LoginRegistrationBFFQryServiceImpl implements LoginRegistrationBFFQryService {


    private LoginRegistrationQryConfig loginRegistrationBFFQryConfig;
    private CommonService commonUtil;


    @Override
    public Mono<VerifyMobileResp> checkExistingUser(CheckMbRegistrationReq mobile) {

        return Mono.fromSupplier(() -> {
            return commonUtil.getData(null,
                    loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl()
                            + loginRegistrationBFFQryConfig.getVerifyMobile(),
                    mobile, VerifyMobileResp.class, CallType.POST, false);
        });
    }


    @Override
    public Mono<UserRegistrationResp> verifyUserRegistrationDetails(UserRegistrationReq userRegistrationReq) {
        UserRegistrationResp userRegistrationResp = new UserRegistrationResp();

        switch (userRegistrationReq.getOperation()) {

            case LoginRegistrationConstants.GENERATE_OTP:
                GenerateAadharOtpResp generateAadharOtpResp = commonUtil.getData(null,
                        loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl()
                                + loginRegistrationBFFQryConfig.getGenerateOtp(),
                        userRegistrationReq.getGenerateAadharOtp(), GenerateAadharOtpResp.class, CallType.POST, false);
                userRegistrationResp.setGenerateAadharOtpResp(generateAadharOtpResp);

                return Mono.just(userRegistrationResp);


            case LoginRegistrationConstants.VALIDATE_OTP:
                VerifyAadharOtpResp verifyAadharOtpResp = commonUtil.getData(null,
                        loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl()
                                + loginRegistrationBFFQryConfig.getValidateOtp(),
                        userRegistrationReq.getVerifyAadharOtp(), VerifyAadharOtpResp.class, CallType.POST, false);
                userRegistrationResp.setVerifyAadharOtpResp(verifyAadharOtpResp);

                return Mono.just(userRegistrationResp);


            case LoginRegistrationConstants.VALIDATE_DEB_CRD:

                verifyDebitCardResp debitCardRegResp = commonUtil.getData(null,
                        loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl() + loginRegistrationBFFQryConfig.getVerifyDebCrd(),
                        userRegistrationReq.getVerifyDebitCard(), verifyDebitCardResp.class, CallType.POST, false);

                userRegistrationResp.setDebitCardRegResp(debitCardRegResp);

                return Mono.just(userRegistrationResp);

            default:
                log.error("invalid operation type: {}", userRegistrationReq.getOperation());
                throw new LoginRegistrationException(ErrorEnum.INVALID_OPERATION_TYPE);
        }
    }

    @Override
    public Mono<GetSecurityQuestionsResp> getSecurityQuestions(GetSecurityQuestionReq securityQuestionReq) {

        return Mono.fromSupplier(() -> {
            return commonUtil.getData(null,
                    loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl()
                            + loginRegistrationBFFQryConfig.getSecurityQuestions(),
                    securityQuestionReq, GetSecurityQuestionsResp.class, CallType.POST, false);
        });
    }

    @Override
    public Mono<CorpLoginResp> corpLogin(CoprpLoginReq coprpLoginReq) {
        return Mono.fromSupplier(() -> {
            return commonUtil.getData(null,
                    loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl()
                            + loginRegistrationBFFQryConfig.getCorpUserLogin(),
                    coprpLoginReq, CorpLoginResp.class, CallType.POST, false);
        });
    }

    @Override
    public Mono<GenerateOtpResp> generateTpinOtp(GenerateOtpReq generateOtpReq) {
        return Mono.fromSupplier(() -> {
            return commonUtil.getData(null,
                    loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl()
                            + loginRegistrationBFFQryConfig.getGenerateTpinOtp(),
                    generateOtpReq, GenerateOtpResp.class, CallType.POST, false);
        });
    }

    @Override
    public Mono<VerifyOtpResp> verifyTpinOtp(VerifyOtpReq verifyOtpReq) {
        return Mono.fromSupplier(() -> {
            return commonUtil.getData(null,
                    loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl()
                            + loginRegistrationBFFQryConfig.getVerifyTpinOtp(),
                    verifyOtpReq, VerifyOtpResp.class, CallType.POST, false);
        });
    }

    @Override
    public Mono<GenericResponse> verifyOldloginMpin(VerifyOldLoginMPin verifyOldLoginMPin) {
        if (Event.isValidEvent(verifyOldLoginMPin.getEvent())) {
            return Mono.fromSupplier(() -> {
                return commonUtil.getData(null,
                        loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl()
                                + loginRegistrationBFFQryConfig.getVerifyOldLoginMpinEndpoint(),
                        verifyOldLoginMPin, GenericResponse.class, CallType.POST, false);

            });
        }
        throw new LoginRegistrationException(ErrorEnum.NO_VALID_EVENT_TYPE);

    }

    @Override
    public Mono<CheckMbRegistrationResp> checkMbRegistration(CheckMbRegistrationReq checkMbRegistrationReq) {
        return Mono.fromSupplier(() -> {
            return commonUtil.getData(null,
                    loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl()
                            + loginRegistrationBFFQryConfig.getCheckMbRegistration(),
                    checkMbRegistrationReq, CheckMbRegistrationResp.class, CallType.POST, false);
        });
    }

    @Override
    public Mono<GenericResponse> verifyTpin(VerifyTpin verifyTpin) {
        return Mono.fromSupplier(() -> {
            return commonUtil.getData(null,
                    loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl()
                            + loginRegistrationBFFQryConfig.getVerifyTpinEndpoint(),
                    verifyTpin, GenericResponse.class, CallType.POST, false);
        });
    }

    @Override
    public Mono<KnowledgeQuestionsResp> getAllKnowledgeQuestions() {
        return Mono.fromSupplier(() -> {
            return commonUtil.getData(null,
                    loginRegistrationBFFQryConfig.getLoginRegistrationQrySvcBaseUrl()
                            + loginRegistrationBFFQryConfig.getKnowledgeQuestions(),
                    null, KnowledgeQuestionsResp.class, CallType.POST, false);
        });
    }


}
