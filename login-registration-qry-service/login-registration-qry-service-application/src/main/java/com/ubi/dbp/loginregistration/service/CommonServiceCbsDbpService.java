package com.ubi.dbp.loginregistration.service;

import com.ubi.dbp.loginregistration.model.CheckMbRegistrationReq;
import com.ubi.dbp.loginregistration.model.VerifyMobileResp;

import reactor.core.publisher.Mono;

public interface CommonServiceCbsDbpService {
	
	Mono<VerifyMobileResp> ConcurrentCallCbsDbp(CheckMbRegistrationReq mobile);
}
