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

import java.util.Properties;

/**
 * config data 기본형
 * 아무것도 설정되지 않았을때 (임의 메모리만 사용할 떄)
 * @author macle
 */
public class PropertiesConfigData extends ConfigData{
    
    private final Properties properties = new Properties();

    private int priority = ConfigSet.XML_FILE_PRIORITY+1;


    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String getConfig(String key) {
        return properties.getProperty(key);
    }

    @Override
    public boolean containsKey(String key) {
        return properties.containsKey(key);
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void put(String key, String value) {
        properties.put(key, value);
    }

    @Override
    public String remove(String key) {
        Object obj = properties.remove(key);

        if(obj == null){
            return null;
        }
        if(obj.getClass() == String.class){
            return (String) obj;
        }

        return obj.toString();
    }
}
