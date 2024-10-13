package com.seomse.commons.config;

import java.util.Map;

/**
 * @author macle
 */
public class MapConfigData extends ConfigData{
    private final Map<String, String> map ;

    private int priority = ConfigSet.XML_FILE_PRIORITY+1;
    public MapConfigData(Map<String, String> map){
        this.map =map;

    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String getConfig(String key) {
        return map.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void put(String key, String value) {
        map.put(key, value);
    }

    @Override
    public String remove(String key) {
        return map.remove(key);
    }
}
