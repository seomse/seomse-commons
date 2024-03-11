package com.seomse.commons.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author macle
 */
public class GsonUtils {

    public static final Gson GSON = new Gson();

    public static String toJson(JsonObject jsonObject){
        return GSON.toJson(jsonObject);
    }
    public static String toJson(JsonArray jsonArray){
        return GSON.toJson(jsonArray);
    }

    public static JsonObject fromJsonObject(String jsonText){
        return GSON.fromJson(jsonText, JsonObject.class);
    }


    public static JsonArray fromJsonArray(String jsonText){
        return GSON.fromJson(jsonText, JsonArray.class);
    }

}
