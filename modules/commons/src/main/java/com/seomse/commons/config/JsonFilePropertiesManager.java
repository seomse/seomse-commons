package com.seomse.commons.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author macle
 */
public class JsonFilePropertiesManager {

    private static class Singleton {
        private static final JsonFilePropertiesManager instance = new JsonFilePropertiesManager();
    }
    public static JsonFilePropertiesManager getInstance(){
        return Singleton.instance;
    }

    private final Object lock = new Object();

    private final Map<String, JsonFileProperties> pathMap = new HashMap<>();

    public JsonFileProperties getByName(String name){
        String path = ConfigSet.CONFIG_DIR_PATH + "/" + name;


        return getByPath(path);
    }

    public JsonFileProperties getByPath(String path){
        synchronized (lock){
            JsonFileProperties jsonFileProperties = pathMap.get(path);
            if(jsonFileProperties == null){
                jsonFileProperties = new JsonFileProperties(path);
                pathMap.put(path, jsonFileProperties);
            }

            return jsonFileProperties;
        }
    }

}
