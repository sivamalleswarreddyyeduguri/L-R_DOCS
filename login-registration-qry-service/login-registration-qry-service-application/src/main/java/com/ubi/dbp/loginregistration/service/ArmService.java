package com.ubi.dbp.loginregistration.service;

import com.ubi.dbp.loginregistration.model.CheckMbRegistrationReq;
import com.ubi.dbp.loginregistration.model.DbpResp;

import reactor.core.publisher.Mono;

public interface ArmService {

    Mono<DbpResp> getUserByMbDbp(CheckMbRegistrationReq mobile);
}
