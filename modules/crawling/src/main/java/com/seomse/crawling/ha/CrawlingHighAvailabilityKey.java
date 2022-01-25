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
package com.seomse.crawling.ha;
/**
 * crawling ha 기능에서 사용 하는 key
 * @author macle
 */
public class CrawlingHighAvailabilityKey {

    public static final String SERVICE_FLAG  = "crawling.service.flag";

    public static final String ACTIVE_ENGINE_ID  = "crawling.active.engine.id";

    public static final String INITIALIZER_PACKAGE  = "crawling.initializer.package";

    public static final String ACTIVE_PRIORITY  = "crawling.active.priority";

    public static final String STAND_BY_CHECK_SECOND = "crawling.stand.by.check.second";

}
