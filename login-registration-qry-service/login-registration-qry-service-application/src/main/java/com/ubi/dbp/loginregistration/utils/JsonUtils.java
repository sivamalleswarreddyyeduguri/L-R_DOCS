package com.ubi.dbp.loginregistration.utils;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
    		.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
    		.registerTypeAdapter(Instant.class, new InstantTypeAdapter())
            .create();

    public static <T> T jsonToPojo(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
    
    public static <T> T jsonToPojo(String jsonArray, Type t) {
        return gson.fromJson(jsonArray, t);
    }
    
    public static <T> String toJson(T t) {
    	return gson.toJson(t);
    }
}