package com.ubi.dbp.loginregistration.service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.ubi.dbp.loginregistration.exception.LoginRegistrationException;
import com.ubi.dbp.loginregistration.exception.ErrorConst;
import com.ubi.dbp.loginregistration.utils.JSONHeader;
import com.ubi.dbp.loginregistration.utils.JsonUtils;

import dbp.framework.proxy.common.client.DbpWebClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public abstract class AbsCommonService {

	DbpWebClient dbpWebClient;
	Map<String, String> header = JSONHeader.getHeader();
	Map<String, String> pathParam = new HashMap<>();
	public enum CallType {
		GET, POST
	}

	public AbsCommonService(DbpWebClient dbpWebClient) {
		this.dbpWebClient = dbpWebClient;
	}

	public <T, R> Mono<R> handle(String url, T request, Class<R> clazz, CallType callType) {
		Mono<String> response = executeRequest(url, request, callType);
		return response.map(resp -> JsonUtils.jsonToPojo(resp, clazz));
	}
	
	public <T, R> Mono<R> handle(String url, T request, Type type, CallType callType) {
		Mono<String> response = executeRequest(url, request, callType);
		return response.map(resp -> JsonUtils.jsonToPojo(resp, type));
	}

	protected <T> Mono<String> executeRequest(String url, T request, CallType callType) {
		try {
		switch (callType) {
		case GET:
			return dbpWebClient.handleGetRequest(url, pathParam, header, String.class);
		case POST:
			return dbpWebClient.handlePostRequest(request, url, pathParam, header, String.class);
		default:
			throw new RuntimeException("Invalid call type");
		}
		}catch(Exception e) {
			log.error("Error while calling service ",e);
			throw new LoginRegistrationException(ErrorConst.UNABLE_TO_PROCESS, e.getMessage());
		}
	}
}
