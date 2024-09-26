package com.ubi.dbp.loginregistration.bff.service;
import java.lang.reflect.Type;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.ubi.dbp.loginregistration.bff.util.JSONHeader;
import com.ubi.dbp.loginregistration.bff.util.JsonUtils;

import dbp.framework.proxy.common.client.DbpHttpClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbsCommonService {

	CacheService cacheService;
	DbpHttpClient dbpHttpClient;
	Map<String, String> header = JSONHeader.getHeader();

	public enum CallType {
		GET, POST
	}

	public AbsCommonService(CacheService cacheService, DbpHttpClient dbpHttpClient) {
		this.cacheService = cacheService;
		this.dbpHttpClient = dbpHttpClient;
	}

	public <T, R> R getData(String key, String url, T request, Class<R> clazz, CallType callType, boolean isCacheable ) {
		if(isCacheable) {
			R cachedRes = cacheService.getFromCache(key, clazz);
			if (cachedRes != null) {
				log.info("data recieved from cache{}");
				return cachedRes;
			}
		}
		String response = executePostRequest(url, request, callType);
		if (isCacheable && StringUtils.isNotBlank(response)) {
			cacheService.putInCache(response, key);
		}
		return JsonUtils.jsonToPojo(response, clazz);
	}
	 
	public <T, R> R getData(String key, String url, T request, Type type, CallType callType, boolean isCacheable ) {
		if (isCacheable) {
			R cachedRes = cacheService.getFromCache(key, type);
			if (cachedRes != null) {
				log.info("data recieved from cache{}");
				return cachedRes;
			}
		}
		String response = executePostRequest(url, request, callType);
		if (isCacheable && StringUtils.isNotBlank(response)) {
			cacheService.putInCache(response, key);
		}
		return JsonUtils.jsonToPojo(response, type);
	}

	public <T> String executePostRequest(String url, T request, CallType callType) {
		switch (callType) {
		case GET:
			return dbpHttpClient.executeGetRequest(url);
		case POST:
			return dbpHttpClient.executePostRequest(new StringEntity(JsonUtils.toJson(request)), header, url);
		default:
			throw new RuntimeException("Invalid call type");
		}
	}
}
