package com.seomse.commons.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.math.BigInteger;

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

    public static JsonArray getArray(String [] array){

        if(array == null){
            return null;
        }
        JsonArray jsonArray = new JsonArray();
        for(String value : array){
            jsonArray.add(value);
        }
        return jsonArray;
    }


    public static JsonArray getArray(int [] array){

        if(array == null){
            return null;
        }
        JsonArray jsonArray = new JsonArray();
        for(int value : array){
            jsonArray.add(value);
        }
        return jsonArray;
    }


    public static JsonArray getArray(double [] array){

        if(array == null){
            return null;
        }
        JsonArray jsonArray = new JsonArray();
        for(double value : array){
            jsonArray.add(value);
        }
        return jsonArray;
    }

    public static JsonArray getArray(long [] array){

        if(array == null){
            return null;
        }
        JsonArray jsonArray = new JsonArray();
        for(long value : array){
            jsonArray.add(value);
        }
        return jsonArray;
    }


    public static JsonArray getArray(Number[] array){

        if(array == null){
            return null;
        }
        JsonArray jsonArray = new JsonArray();
        for(Number value : array){
            jsonArray.add(value);
        }
        return jsonArray;
    }

    public static String [] getString(JsonArray jsonArray){

        String [] array = new String[jsonArray.size()];
        for (int i = 0; i <array.length ; i++) {
            array[i] = jsonArray.get(i).getAsString();
        }

        return array;
    }

    public static int [] getInts(JsonArray jsonArray){

        int [] array = new int[jsonArray.size()];
        for (int i = 0; i <array.length ; i++) {
            array[i] = jsonArray.get(i).getAsInt();
        }

        return array;
    }

    public static double [] getDoubles(JsonArray jsonArray){

        double [] array = new double[jsonArray.size()];
        for (int i = 0; i <array.length ; i++) {
            array[i] = jsonArray.get(i).getAsDouble();
        }

        return array;
    }

    public static long [] getLongs(JsonArray jsonArray){

        long [] array = new long[jsonArray.size()];
        for (int i = 0; i <array.length ; i++) {
            array[i] = jsonArray.get(i).getAsLong();
        }

        return array;
    }

    public static Number [] getNumbers(JsonArray jsonArray){

        Number [] array = new Number[jsonArray.size()];
        for (int i = 0; i <array.length ; i++) {
            array[i] = jsonArray.get(i).getAsNumber();
        }

        return array;
    }

    public static BigDecimal [] getBigDecimals(JsonArray jsonArray){

        BigDecimal [] array = new BigDecimal[jsonArray.size()];
        for (int i = 0; i <array.length ; i++) {
            array[i] = jsonArray.get(i).getAsBigDecimal();
        }

        return array;
    }

    public static BigInteger[] getBigIntegers(JsonArray jsonArray){

        BigInteger [] array = new BigInteger[jsonArray.size()];
        for (int i = 0; i <array.length ; i++) {
            array[i] = jsonArray.get(i).getAsBigInteger();
        }

        return array;
    }
}
