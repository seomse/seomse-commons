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

package com.seomse.sync;
/**
 * 동기화
 * 이 클래스를 implements 하여 구현 하고 
 * Synchronization annotation 을 지정 하거나
 * SynchronizerManager 인스턴스에 추가함
 * @author macle
 */
public interface Synchronizer {
    /**
     * 동기화
     */
    void sync();

}
