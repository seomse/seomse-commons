package com.seomse.commons.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.seomse.commons.utils.ExceptionUtil;
import com.seomse.commons.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.math.BigDecimal;

/**
 * @author macle
 */
@Slf4j
public class JsonFileProperties {

    private final String filePath;

    private final JsonObject jsonObject;

    private final Gson gson =  new GsonBuilder().setPrettyPrinting().create();

    private final Object lock = new Object();

    public JsonFileProperties(String filePath) {

        this.filePath = filePath;

        File file = new File(filePath);
        JsonObject jsonObj;
        if(file.isFile()){
            String fileValue = FileUtil.getFileContents(file, "UTF-8");
            try{
                jsonObj = gson.fromJson(fileValue, JsonObject.class);
            }catch(Exception e){
                jsonObj = new JsonObject();
                log.error(ExceptionUtil.getStackTrace(e));
            }
        }else{
            jsonObj = new JsonObject();
        }
        this.jsonObject = jsonObj;
    }

    public void set(String key, JsonObject value){
        synchronized (lock) {

            jsonObject.add(key, value);
            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }

    public void set(String key, JsonArray value){
        synchronized (lock) {
            jsonObject.add(key, value);
            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }

    public void set(String key, String value){
        synchronized (lock) {

            jsonObject.addProperty(key, value);

            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }


    public void set(String key, Number value){
        synchronized (lock) {
            jsonObject.addProperty(key, value);

            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }

    public void set(String key, char value){
        synchronized (lock) {
            jsonObject.addProperty(key, value);

            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }

    public void set(String key, long value){
        synchronized (lock) {
            jsonObject.addProperty(key, value);

            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }

    public void set(String key, int value){
        synchronized (lock) {
            jsonObject.addProperty(key, value);

            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }

    public void set(String key, double value){
        synchronized (lock) {
            jsonObject.addProperty(key, value);

            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }

    public void set(String key, String [] array){
        synchronized (lock) {

            JsonArray jsonArray = new JsonArray();
            for(String value : array){
                jsonArray.add(value);
            }

            jsonObject.add(key, jsonArray);

            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }

    public void set(String key, int [] array){
        synchronized (lock) {

            JsonArray jsonArray = new JsonArray();
            for(int value : array){
                jsonArray.add(value);
            }

            jsonObject.add(key, jsonArray);

            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }


    public void set(String key, long [] array){
        synchronized (lock) {

            JsonArray jsonArray = new JsonArray();
            for(long value : array){
                jsonArray.add(value);
            }

            jsonObject.add(key, jsonArray);

            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }

    public void set(String key, double [] array){
        synchronized (lock) {

            JsonArray jsonArray = new JsonArray();
            for(double value : array){
                jsonArray.add(value);
            }

            jsonObject.add(key, jsonArray);

            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }


    public void set(String key, Number [] array){
        synchronized (lock) {

            JsonArray jsonArray = new JsonArray();
            for(Number value : array){
                jsonArray.add(value);
            }

            jsonObject.add(key, jsonArray);

            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }


    public void remove(String key){
        synchronized (lock) {
            jsonObject.remove(key);
            String jsonValue = gson.toJson(jsonObject);
            FileUtil.fileOutput(jsonValue, filePath, false);
        }
    }


    public String getString(String key){
        synchronized (lock) {
           return jsonObject.get(key).getAsString();
        }
    }

    public BigDecimal getBigDecimal(String key){
        synchronized (lock) {
            return jsonObject.get(key).getAsBigDecimal();
        }
    }

    public Number getNumber(String key){
        synchronized (lock) {
            return jsonObject.get(key).getAsNumber();
        }
    }

    public int getInt(String key, int defaultValue){
        synchronized (lock) {
            if(!jsonObject.has(key)){
                return defaultValue;
            }
            return jsonObject.get(key).getAsInt();
        }
    }

    public long getLong(String key, long defaultValue){
        synchronized (lock) {
            if(!jsonObject.has(key)){
                return defaultValue;
            }

            return jsonObject.get(key).getAsLong();
        }
    }

    public double getDouble(String key, double  defaultValue){
        synchronized (lock) {
            if(!jsonObject.has(key)){
                return defaultValue;
            }
            return jsonObject.get(key).getAsDouble();
        }
    }

    public JsonObject getJsonObject(String key){
        synchronized (lock) {
            return jsonObject.get(key).getAsJsonObject();
        }
    }

    public JsonArray getJsonArray(String key){
        synchronized (lock) {
            return jsonObject.get(key).getAsJsonArray();
        }
    }


    public String [] getStrings(String key){
        synchronized (lock) {

            JsonArray jsonArray = jsonObject.get(key).getAsJsonArray();
            String [] array = new String[jsonArray.size()];

            for (int i = 0; i <array.length ; i++) {
                array[i] = jsonArray.get(i).getAsString();
            }
            
            return array;
        }
    }


    public int [] getInts(String key){
        synchronized (lock) {
            JsonArray jsonArray = jsonObject.get(key).getAsJsonArray();

            int [] array = new int[jsonArray.size()];
            for (int i = 0; i <array.length ; i++) {
                array[i] = jsonArray.get(i).getAsInt();
            }

            return array;
        }
    }
//
    public long [] getLongs(String key){
        synchronized (lock) {
            JsonArray jsonArray = jsonObject.get(key).getAsJsonArray();

            long [] array = new long[jsonArray.size()];
            for (int i = 0; i <array.length ; i++) {
                array[i] = jsonArray.get(i).getAsLong();
            }

            return array;
        }
    }

    public double [] getDoubles(String key){
        synchronized (lock) {
            JsonArray jsonArray = jsonObject.get(key).getAsJsonArray();

            double [] array = new double[jsonArray.size()];
            for (int i = 0; i <array.length ; i++) {
                array[i] = jsonArray.get(i).getAsDouble();
            }

            return array;
        }
    }

    public Number [] getNumbers(String key){
        synchronized (lock) {
            JsonArray jsonArray = jsonObject.get(key).getAsJsonArray();

            Number [] array = new Number[jsonArray.size()];
            for (int i = 0; i <array.length ; i++) {
                array[i] = jsonArray.get(i).getAsNumber();
            }

            return array;
        }
    }


    public BigDecimal [] getBigDecimals(String key){
        synchronized (lock) {
            JsonArray jsonArray = jsonObject.get(key).getAsJsonArray();

            BigDecimal [] array = new BigDecimal[jsonArray.size()];
            for (int i = 0; i <array.length ; i++) {
                array[i] = jsonArray.get(i).getAsBigDecimal();
            }

            return array;
        }
    }

    public String getJsonValue(){
        synchronized (lock) {
            return gson.toJson(jsonObject);
        }
    }

}
