package com.seomse.crawling;

import com.seomse.commons.utils.time.Times;

import java.util.HashMap;
import java.util.Map;

/**
 * 수집 사이트별 접근시간관리
 * @author macle
 */
public class AccessTimeManager {
    private static class Singleton {
        private static final AccessTimeManager instance = new AccessTimeManager();
    }

    public static AccessTimeManager getInstance(){
        return Singleton.instance;
    }

    private final Object lock = new Object();

    private final Map<String, Long> timeMap = new HashMap<>();


    public long getSleepTime(String key, long waitTime){
        synchronized (lock){

            long now = System.currentTimeMillis();

            Long time = timeMap.get(key);
            if(time == null){
                timeMap.put(key, now);
                return 0;
            }

            long nextTime = time + waitTime;

            if(now > nextTime){
                timeMap.put(key, now);
                return 0;
            }

            timeMap.put(key, nextTime);
            return nextTime - now;

        }

    }


}
