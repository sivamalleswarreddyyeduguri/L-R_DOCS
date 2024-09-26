package com.ubi.dbp.loginregistration.bff.service;

import com.ubi.dbp.loginregistration.bff.dto.FileData;
import com.ubi.dbp.loginregistration.bff.model.TAndCRequest;

import reactor.core.publisher.Mono;

public interface LoginRegistrationBFFService {

	public Mono<FileData> getTermsAndCondition(TAndCRequest tAndCRequest);
}