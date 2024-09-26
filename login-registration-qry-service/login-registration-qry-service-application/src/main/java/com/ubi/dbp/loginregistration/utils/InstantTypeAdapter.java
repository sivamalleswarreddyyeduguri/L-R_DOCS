package com.ubi.dbp.loginregistration.utils;


import java.time.Instant;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class InstantTypeAdapter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {

	//private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public Instant deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		//return Instant.parse(json.getAsString(), formatter);
		return Instant.parse(json.getAsString());
	}

	@Override
	public JsonElement serialize(Instant src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}