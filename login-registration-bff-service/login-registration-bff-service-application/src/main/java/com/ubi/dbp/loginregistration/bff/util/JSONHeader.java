package com.ubi.dbp.loginregistration.bff.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class JSONHeader {
	
	private static Map<String, String> header;

	public static Map<String, String> getHeader() {
		header = new HashMap<String, String>();
		header.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return header;
	}

}
