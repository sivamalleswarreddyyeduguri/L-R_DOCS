package com.ubi.dbp.loginregistration.bff.service.impl;

import com.ubi.dbp.loginregistration.bff.config.LoginRegistrationBFFCmdConfig;
import com.ubi.dbp.loginregistration.bff.dto.*;
import com.ubi.dbp.loginregistration.bff.service.AbsCommonService;
import com.ubi.dbp.loginregistration.bff.enums.Event;
import com.ubi.dbp.loginregistration.bff.exception.ErrorEnum;
import com.ubi.dbp.loginregistration.bff.exception.LoginRegistrationException;
import com.ubi.dbp.loginregistration.bff.service.AbsCommonService.CallType;
import com.ubi.dbp.loginregistration.bff.service.LoginRegistrationBFFCmdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class LoginRegistrationBFFCmdServiceImpl implements LoginRegistrationBFFCmdService {

    private final LoginRegistrationBFFCmdConfig loginRegistrationCmdConfig;

    private final CommonService commonUtil;

    @Override
    public Mono<GenericResponse> setTPin(SetTPinReq setTPinReq) {
        if (Event.isValidEvent(setTPinReq.getEvent())) {
            return Mono.fromSupplier(() -> {
                return commonUtil.getData(null,
                        loginRegistrationCmdConfig.getLoginRegistrationCmdSvcBaseUrl()
                                + loginRegistrationCmdConfig.getLoginRegistrationCmdPath()
                                + loginRegistrationCmdConfig.getSetTpin(),
                        setTPinReq, GenericResponse.class, CallType.POST, false);
            });
        }
        throw new LoginRegistrationException(ErrorEnum.NO_VALID_EVENT_TYPE);

    }

    @Override
    public Mono<GenericResponse> setMpin(SetMpinReq setMpinReq) {
        if (Event.isValidEvent(setMpinReq.getEvent())) {
            return Mono.fromSupplier(() -> {
                return commonUtil.getData(null,
                        loginRegistrationCmdConfig.getLoginRegistrationCmdSvcBaseUrl()
                                + loginRegistrationCmdConfig.getLoginRegistrationCmdPath()
                                + loginRegistrationCmdConfig.getSetMpin(),
                        setMpinReq, GenericResponse.class, CallType.POST, false);

            });
        }
        throw new LoginRegistrationException(ErrorEnum.NO_VALID_EVENT_TYPE);

    }


    @Override
    public Mono<GenericResponse> setSecurityQuestion(SetSecurityQuestion setSecurityQuestion) {
        return Mono.fromSupplier(() -> {
            return commonUtil.getData(null,
                    loginRegistrationCmdConfig.getLoginRegistrationCmdSvcBaseUrl() + loginRegistrationCmdConfig.getLoginRegistrationCmdPath()
                            + loginRegistrationCmdConfig.getSecurityQuestions(),
                    setSecurityQuestion, GenericResponse.class, AbsCommonService.CallType.POST, false);
        });
    }

    @Override
    public Mono<GenericResponse> setPassword(SetPasswordReq setPasswordReq) {
        return Mono.fromSupplier(() -> {
            return commonUtil.getData(null,
                    loginRegistrationCmdConfig.getLoginRegistrationCmdSvcBaseUrl() + loginRegistrationCmdConfig.getLoginRegistrationCmdPath()
                            + loginRegistrationCmdConfig.getPassword(),
                    setPasswordReq, GenericResponse.class, AbsCommonService.CallType.POST, false);
        });
    }

    @Override
    public Mono<GenericResponse> mbRegistration(MbRegistrationReq mbRegistrationReq) {
        return Mono.fromSupplier(() -> {
            return commonUtil.getData(null,
                    loginRegistrationCmdConfig.getLoginRegistrationCmdSvcBaseUrl()+ loginRegistrationCmdConfig.getLoginRegistrationCmdPath()
                            + loginRegistrationCmdConfig.getMbRegistration(),
                    mbRegistrationReq, GenericResponse.class, AbsCommonService.CallType.POST, false);
        });
    }
}

