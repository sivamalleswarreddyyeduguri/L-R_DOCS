package com.ubi.dbp.loginregistration.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTypeAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public LocalDate deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		return LocalDate.parse(json.getAsString(), formatter);
	}

 	@Override
 	public JsonElement serialize(LocalDate date, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
 		return new JsonPrimitive(date.format(formatter));
 	}

}