package com.seomse.crypto;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 싱글턴으로 사용되지만 개별로 사용할 수 있어서 public 로 설정
 * @author macle
 */
public class CharMapManager {

    private static class Singleton {
        private static final CharMapManager instance = new CharMapManager();
    }

    public static CharMapManager getInstance(){
        return Singleton.instance;
    }

    
    
    private final Map<String, CharMap> map = new HashMap<>();


    public CharMapManager(){

    }


    public void put(String id, CharMap charMap){
        map.put(id, charMap);
    }

    public CharMap getCharMap(String id){
        return map.get(id);
    }

    public CharMap remove(String id){
        return map.remove(id);
    }

    public Map<String, CharMap> getDataMap() {
        return map;
    }


    @Override
    public String toString(){

        if(map.size() == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();

        Set<String> keys = map.keySet();
        for(String key : keys){
            sb.append("\n").append(key).append(":").append(map.get(key).toString());
        }


        return sb.substring(1);
    }

}
