package com.ubi.dbp.loginregistration.bff.service;

import java.lang.reflect.Type;

public interface CacheService {

	public void putInCache(String data, String key);
	public <R> R getFromCache(String key, Class<R> clazz);
	public <R> R getFromCache(String key, Type type);
}
