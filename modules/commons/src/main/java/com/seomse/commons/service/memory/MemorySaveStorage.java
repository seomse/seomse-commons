package com.seomse.commons.service.memory;

import java.util.HashSet;
import java.util.Set;

/**
 * 메모리로 관리하는 개체가 제때 해제를 못해주어 out of memory 방지를 위해 메모리 정보 개체를 관리한다
 * @author macle
 */
@SuppressWarnings("rawtypes")
public class MemorySaveStorage {
    private static class Singleton {
        private static final MemorySaveStorage instance = new MemorySaveStorage();
    }

    public static MemorySaveStorage getInstance(){
        return Singleton.instance;
    }

    private final Set<MemorySaveManager> set = new HashSet<>();
    private MemorySaveManager [] array = new MemorySaveManager[0];
    private final Object lock = new Object();

    private MemorySaveStorage(){

    }

    public void add(MemorySaveManager memorySaveManager){
        synchronized (lock ){
            if(set.add(memorySaveManager)){
                //메모리 정보가 변경되면 객체변경
                array = set.toArray(new MemorySaveManager[0]);
            }
        }
    }

    public void remove(MemorySaveManager memorySaveManager){
        synchronized (lock ){
            if(set.remove(memorySaveManager)){
                //메모리 정보가 변경되면 객체변경
                array = set.toArray(new MemorySaveManager[0]);
            }
        }
    }

    public MemorySaveManager[] getArray() {
        return array;
    }
}
