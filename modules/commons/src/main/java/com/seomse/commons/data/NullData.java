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

package com.seomse.commons.data;

/**
 * 공통 적으로 사용 하는 null 대신에 방지용 정리
 * @author macle
 */
public class NullData {

    /**
     * String [] 을 null 이 아닌 String [0] 으로 생성 해서 전달할 때 사용
     */
    public static final String [] EMPTY_STRING_ARRAY = new String[0];

}
