package com.seomse.commons.example;


import com.seomse.commons.config.JsonFileProperties;
import com.seomse.commons.config.JsonFilePropertiesManager;

/**
 * @author macle
 */
public class JsonFilePropertiesExample {
    public static void set(){
        JsonFileProperties jsonFileProperties = JsonFilePropertiesManager.getInstance().getByPath("config/collect_time.json");
        jsonFileProperties.set("A", System.currentTimeMillis());
        jsonFileProperties.set("B", System.currentTimeMillis());
    }
    public static void view(){
        JsonFileProperties jsonFileProperties = JsonFilePropertiesManager.getInstance().getByPath("config/collect_time.json");
        System.out.println(jsonFileProperties.getLong("A", 0));
        System.out.println(jsonFileProperties.getLong("B", 0));
    }

    public static void main(String[] args) {
        view();
    }
}
