package com.seomse.commons.config;

import java.util.ArrayList;
import java.util.List;

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
 *  버    전 : 1.1
 *  수정이력 : 2019.10.27
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


    public abstract boolean containsKey(String key);

    /**
     * 호출 우선순위
     * 필수구현
     * @return 우선순위
     */
    public abstract int getPriority();

    protected abstract void put(String key, String value);

    /**
     * 설정삭제
     * @param key key
     * @return 삭제 객체
     */
    protected abstract Object remove(String key);

    /**
     * 초기 설정이 끝나고 업데이트 될경우
     * 설정하기
     * @param key 설정키
     * @param value 설정된 값
     * @return 변화여부
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

        ConfigInfo [] configInfos = new ConfigInfo[1];
        configInfos[0] = new ConfigInfo(key, value);
        Config.notify(this, configInfos);
        return true;
    }

    public boolean setConfig(ConfigInfo configInfo){
        if(configInfo.isDelete){
            Object obj = remove(configInfo.key);
            if(obj == null){
                return false;
            }
            ConfigInfo [] configInfos = new ConfigInfo[1];
            configInfos[0] = configInfo;
            Config.notify(this, configInfos);
            return true;
        }

        String lastValue = getConfig(configInfo.key);
        if(lastValue != null && lastValue.equals(configInfo.value)){
            return false;
        }

        ConfigInfo [] configInfos = new ConfigInfo[1];
        configInfos[0] = configInfo;
        Config.notify(this, configInfos);
        return true;

    }

    /**
     * 설정하기
     * 여러정보 동시
     * 초기 세팅이 완료된후
     * 이후 변경 과정 업데이트
     * 반드시 Config 클래스에 notify 시킬것
     * @param configInfos configInfo array
     */
    public void setConfig(ConfigInfo [] configInfos){
        List<ConfigInfo> changeList = null;
        for(ConfigInfo configInfo : configInfos){

            if(configInfo.isDelete){
                Object obj = remove(configInfo.key);
                if(obj == null){
                    continue;
                }
                if(changeList == null){
                    changeList = new ArrayList<>();
                }
                changeList.add(configInfo);
            }

            String lastValue = getConfig(configInfo.key);
            if(lastValue != null && lastValue.equals(configInfo.value)){
                continue;
            }

            if(changeList == null){
                changeList = new ArrayList<>();
            }

            put(configInfo.key, configInfo.value);
            changeList.add(configInfo);
        }

        if (changeList == null) {
            return;
        }

        ConfigInfo [] changeArray = changeList.toArray(new ConfigInfo[0]);
        Config.notify(this, changeArray);
        changeList.clear();
    }

}
