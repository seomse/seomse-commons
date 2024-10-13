/*
 * Copyright (C) 2020 Seomse Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.seomse.commons.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * xml로 읽어 오는 설정 정보
 * @author macle
 */
public class XmlFileConfigData extends ConfigData{

    private final Properties props;

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
    public boolean containsKey(String key) {
        return props.containsKey(key);
    }



    @Override
    public int getPriority() {
        return ConfigSet.XML_FILE_PRIORITY;
    }

    @Override
    public void put(String key, String value) {
        props.put(key, value);
    }

    @Override
    public String remove(String key) {
        Object obj =props.remove(key);

        if(obj == null){
            return null;
        }
        if(obj.getClass() == String.class){
            return (String) obj;
        }

        return obj.toString();
    }


}
