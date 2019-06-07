package com.seomse.commons.config;
/**
 * <pre>
 *  파 일 명 : XmlFileConfigData.java
 *  설    명 : xml 파일 설정 데이터
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.05.29
 *  버    전 : 1.0
 *  수정이력 :
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
    public String getConfig(String key) {
        return System.getProperties().getProperty(key);
    }

    @Override
    public int getPriority() {
        return ConfigSet.SYSTEM_PROPERTIES_PRIORITY;
    }
}
