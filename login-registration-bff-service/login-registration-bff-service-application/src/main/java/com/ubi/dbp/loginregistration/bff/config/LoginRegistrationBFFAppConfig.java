package com.ubi.dbp.loginregistration.bff.config;

import dbp.framework.proxy.common.client.DbpHttpClient;
import dbp.framework.proxy.common.client.DbpHttpClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginRegistrationBFFAppConfig {
	@Bean
	public DbpHttpClient createDbpHttpClient() {
		return (DbpHttpClient) new DbpHttpClientImpl();
	}
	
}
