package com.seomse.crypto;

import java.util.HashMap;
import java.util.Map;

/**
 * 싱글턴으로 사용되지만 개별로 사용할 수 있어서 public 로 설정
 * @author macle
 */
public class ChatMapManager {

    private static class Singleton {
        private static final ChatMapManager instance = new ChatMapManager();
    }

    public static ChatMapManager getInstance(){
        return Singleton.instance;
    }

    
    
    private final Map<String, CharMap> map = new HashMap<>();


    public ChatMapManager(){

    }


    public void put(String id, CharMap charMap){
        map.put(id, charMap);
    }

    public CharMap remove(String id){
        return map.remove(id);
    }


}
