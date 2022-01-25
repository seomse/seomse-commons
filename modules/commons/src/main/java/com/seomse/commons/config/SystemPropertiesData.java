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

/**
 * 시스템 설정 정보
 * @author macle
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
