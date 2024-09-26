package com.ubi.dbp.loginregistration.bff.service.impl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ubi.dbp.loginregistration.bff.service.CacheService;
import com.ubi.dbp.loginregistration.bff.util.JSONHeader;
import com.ubi.dbp.loginregistration.bff.util.JsonUtils;

import dbp.framework.proxy.common.client.DbpHttpClient;
import dbp.framework.proxy.common.client.DbpWebClient;
import dbp.framework.support.util.StringData;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CacheServiceImpl implements CacheService{

	private DbpHttpClient dbpHttpClient;
	private DbpWebClient cacheWebClient;
	
	@Value("${loginRegistration.cache.put.url}")
	private String loginregistrationCachePutUrl;
	
	@Value("${loginRegistration.cache.get.url}")
	private String loginregistrationCacheGetUrl;

	@Value("${loginRegistration.cache.name}")
	private String loginregistrationCacheName;

	public CacheServiceImpl(DbpHttpClient dbpHttpClient, DbpWebClient cacheWebClient) {
		this.dbpHttpClient = dbpHttpClient;
		this.cacheWebClient = cacheWebClient;
	}

	Map<String, String> pathParams = new HashMap<String, String>();
	Map<String, String> header = JSONHeader.getHeader();

	@Override
	public void putInCache(String data, String key) {
		StringData stringData = new StringData(key, data);
		try {
			cacheWebClient.handlePostRequest(stringData,
					loginregistrationCachePutUrl.replaceAll("##CACHE_NAME##", loginregistrationCacheName), pathParams, header,
					String.class).subscribe(str -> {
						log.info("Data added to cache for {}"+key);
					});
		} catch (Exception e) {
			log.error("Error while putting data in cache for {}"+key,e);
		}
	}

	@Override
	public <R> R getFromCache(String key, Class<R> clazz) {
		StringData strData = callCacheService(key);
		if (StringUtils.isNotBlank(strData.getData()))
			return JsonUtils.jsonToPojo(strData.getData(), clazz);
		return null;
	}

	@Override
	public <R> R getFromCache(String key, Type type) {
		StringData strData = callCacheService(key);
		if (StringUtils.isNotBlank(strData.getData()))
			return JsonUtils.jsonToPojo(strData.getData(), type);
		return null;
	}

	private StringData callCacheService(String key) {
		String resp = dbpHttpClient.executeGetRequest(
		loginregistrationCacheGetUrl.replaceAll("##CACHE_NAME##", loginregistrationCacheName).replaceAll("##KEY##", key));
		log.info("resp :{} " + resp);
		StringData strData = JsonUtils.jsonToPojo(resp, StringData.class);
		return strData;
	}
	
	}

