/*
 * Copyright (C) 2020 Wigo Inc.
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

package com.seomse.commons.test;

import com.seomse.commons.utils.FileUtil;
import com.seomse.commons.utils.time.TimeUtil;

/**
 * 인메모리 검색 분석 엔진에서 
 * 파엘에 저장된 상세 정보를 가져올때 사용하려고 core 기술 개발중 빠른 line 텍스트 성능 테스트
 * @author macle
 */
public class FileReadLineNumberSpeedTest {
    public static void main(String[] args) {


        String testFilePath = "D:\\seomse\\index\\20200201\\index_0.md";
		for (int i = 0; i <223 ; i++) {
            //일치여부 테스트
			if(!FileUtil.getLineNio(testFilePath, i).equals(FileUtil.getLineIo(testFilePath, i))){
				System.out.println(i);
			}
		}

        long startTime = System.currentTimeMillis();
		for (int i = 0; i <500 ; i++) {
            FileUtil.getLineNio(testFilePath, 77);
		}
        System.out.println("line value Nio 속도: " + TimeUtil.getSecond(System.currentTimeMillis()-startTime));
        startTime = System.currentTimeMillis();
        for (int i = 0; i <500 ; i++) {
            FileUtil.getLineIo(testFilePath, 77);
        }
        System.out.println("line value core 기술 구현체 속도: " + TimeUtil.getSecond(System.currentTimeMillis()-startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i <500 ; i++) {
            FileUtil.getLineNumberNio(testFilePath);
        }
        System.out.println("line count  Nio 속도: " + TimeUtil.getSecond(System.currentTimeMillis()-startTime) + " " + FileUtil.getLineNumberNio(testFilePath));

        startTime = System.currentTimeMillis();
        for (int i = 0; i <500 ; i++) {
            FileUtil.getLineNumberIo(testFilePath);
		}
        System.out.println("line count core 기술 구현체 속도: " + TimeUtil.getSecond(System.currentTimeMillis()-startTime) +" " + FileUtil.getLineNumberIo(testFilePath));

    }
}
