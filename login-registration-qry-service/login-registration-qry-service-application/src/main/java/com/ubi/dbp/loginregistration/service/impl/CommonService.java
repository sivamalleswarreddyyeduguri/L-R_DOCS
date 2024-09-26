package com.ubi.dbp.loginregistration.service.impl;

import org.springframework.stereotype.Component;

import com.ubi.dbp.loginregistration.service.AbsCommonService;

import dbp.framework.proxy.common.client.DbpWebClient;

@Component
public class CommonService extends AbsCommonService{

	public CommonService(DbpWebClient dbpWebClient) {
		super(dbpWebClient);
	}

}
