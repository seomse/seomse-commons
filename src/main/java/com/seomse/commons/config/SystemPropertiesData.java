package com.seomse.commons.config;
/**
 * <pre>
 *  파 일 명 : XmlFileConfigData.java
 *  설    명 : xml 파일 설정 데이터
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.05.29
 *  버    전 : 1.1
 *  수정이력 : 2019.10.26
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class SystemPropertiesData extends ConfigData{

    @Override
    public void put(String key, String value) {
        System.setProperty(key, value);
    }

    @Override
    protected String remove(String key) {
        //시스템 설정은 삭제하지 않음
        return null;
    }

    @Override
    public String getConfig(String key) {
        return System.getProperties().getProperty(key);
    }

    @Override
    public boolean containsKey(String key) {
        return System.getProperties().containsKey(key);
    }



    @Override
    public int getPriority() {
        return ConfigSet.SYSTEM_PROPERTIES_PRIORITY;
    }
}
