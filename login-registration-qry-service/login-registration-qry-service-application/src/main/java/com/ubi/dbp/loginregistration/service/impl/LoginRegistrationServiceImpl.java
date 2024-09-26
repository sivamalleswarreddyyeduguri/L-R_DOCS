
package com.ubi.dbp.loginregistration.service.impl;

import org.springframework.stereotype.Service;
import com.ubi.dbp.loginregistration.model.CheckMbRegistrationReq;
import com.ubi.dbp.loginregistration.model.VerifyMobileResp;
import com.ubi.dbp.loginregistration.service.LoginRegistrationService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class LoginRegistrationServiceImpl implements LoginRegistrationService {

    private final CommonServiceCbsDbpImpl commonServiceCbsDbp;

    public LoginRegistrationServiceImpl(CommonServiceCbsDbpImpl cbsDbp) {
        this.commonServiceCbsDbp = cbsDbp;
    }

    @Override
    public Mono<VerifyMobileResp> checkExistingUser(CheckMbRegistrationReq mobile) {
        return commonServiceCbsDbp.ConcurrentCallCbsDbp(mobile);
    }

}
