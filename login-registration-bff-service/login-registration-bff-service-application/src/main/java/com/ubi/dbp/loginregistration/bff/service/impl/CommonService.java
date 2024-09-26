package com.ubi.dbp.loginregistration.bff.service.impl;

import org.springframework.stereotype.Component;

import com.ubi.dbp.loginregistration.bff.service.AbsCommonService;
import com.ubi.dbp.loginregistration.bff.service.CacheService;

import dbp.framework.proxy.common.client.DbpHttpClient;

@Component
public class CommonService extends AbsCommonService{

	public CommonService(CacheService cacheService, DbpHttpClient dbpHttpClient) {
		super(cacheService, dbpHttpClient);
	}
}
