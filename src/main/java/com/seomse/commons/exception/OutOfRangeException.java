/*
 * Copyright (C) 2021 Seomse Inc.
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

package com.seomse.commons.exception;

/**
 * 범위를 벗어나는 예외
 * @author macle
 */
public class OutOfRangeException extends RuntimeException {

    /**
     * 생성자
     */
    public OutOfRangeException() {
        super();
    }

    /**
     * 생성자
     *
     * @param e 예외
     */
    public OutOfRangeException(Exception e) {
        super(e);
    }

    /**
     * 생성자
     *
     * @param message exception meesage
     */
    public OutOfRangeException(String message) {
        super(message);
    }
}
