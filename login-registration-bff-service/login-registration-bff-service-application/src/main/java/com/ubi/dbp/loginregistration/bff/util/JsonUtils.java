package com.ubi.dbp.loginregistration.bff.util;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonUtilsForLocaleDate.LocalDateAdapter())
    		.registerTypeAdapter(LocalDateTime.class, new JsonUtilsForLocaleDateTime.LocalDateTimeAdapter())
            .create();

    public static <T> T jsonToPojo(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
    
    public static <T> T jsonToPojo(String jsonArray, Type t) {
//        Type type = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(jsonArray, t);
    }
    
    public static <T> String toJson(T t) {
    	return gson.toJson(t);
    }
}