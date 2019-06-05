package com.seomse.commons.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <pre>
 *  파 일 명 : XmlFileConfigData.java
 *  설    명 : xml 파일 설정 데이터
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
public class XmlFileConfigData extends ConfigData{

    private Properties props;

    /**
     * 생성자
     * @param file 설정파일
     * @throws IOException exception
     */
    XmlFileConfigData(File file) throws IOException {
        props = new Properties();

        if(file.exists()) {

            InputStream configInputStream = null;
            //noinspection TryFinallyCanBeTryWithResources,CaughtExceptionImmediatelyRethrown
            try {
                configInputStream = new FileInputStream(file);
                props.loadFromXML(configInputStream);
            } catch (IOException e) {

                throw e;
            } finally {
                if (configInputStream != null) {
                    configInputStream.close();
                }
            }
        }
    }


    @Override
    public String getConfig(String key) {
        return props.getProperty(key);
    }

    @Override
    public int getPriority() {
        return ConfigSet.XML_FILE_PRIORITY;
    }

    @Override
    protected void put(String key, String value) {
        props.put(key, value);
    }
}
