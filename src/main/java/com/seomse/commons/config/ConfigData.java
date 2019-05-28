package com.seomse.commons.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  파 일 명 : ConfigData.java
 *  설    명 : 설정 데이터
 *            설정별 값 저장
 *            설정별 값 우선순위를 위해 사용
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.05.28
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public abstract class ConfigData {



    /**
     * 값 얻기
     * @param key 설정 키
     * @return 저장된 설정된 값
     */
    public abstract String getConfig(String key);

    /**
     * 호출 우선순위
     * 필수구현
     * @return 우선순위
     */
    public abstract int getPriority();

    protected abstract void put(String key, String value);

    /**
     * 초기 설정이 끝나고 업데이트 될경우
     * 설정하기
     * @param key 설정키
     * @param value 설정된 값
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean setConfig(String key, String value){
        if(value == null){
            return false;
        }
        String lastValue = getConfig(key);

        if(lastValue != null && lastValue.equals(value)){
            return false;
        }

        put(key, value);
        Map<String, String> updateConfigMap = new HashMap<>();
        updateConfigMap.put(key, value);
        Config.notify(this, updateConfigMap);
        return true;
    }

    /**
     * 설정하기
     * 여러정보 동시
     * 초기 세팅이 완료된후
     * 이후 변경 과정 업데이트
     * 반드시 Config 클래스에 notify 시킬것
     * @param configInfoList
     */
    public int setConfig(List<ConfigInfo> configInfoList){

        Map<String, String> updateConfigMap = null;
        for(ConfigInfo configInfo : configInfoList){
            String lastValue = getConfig(configInfo.key);
            if(lastValue != null && lastValue.equals(configInfo.value)){
                continue;
            }

            if(updateConfigMap == null){
                updateConfigMap = new HashMap<>();
            }

            put(configInfo.key, configInfo.value);
            updateConfigMap.put(configInfo.key, configInfo.value);

        }

        if (updateConfigMap == null) {
            return 0;
        }
        Config.notify(this, updateConfigMap);
        return updateConfigMap.size();
    }

}
