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
package com.seomse.commons.utils;
/**
 * 코드체계 관련 유틸
 * @author macle
 */
public class CodeUtil {
	
	/**
	 * 자리수만큼 00문자열을 생성해서 돌려줌
	 * @param codeNum int codeNum
	 * @param length int length
	 * @return String CodeNumberValue
	 */
	public static String getCodeNumberValue(int codeNum, int length){
		
		String numValue = Integer.toString(codeNum);
		
		int gap = length - numValue.length();
		
		if(gap == 0){
			return numValue;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0 ; i < gap ; i++){
			sb.append("0");
		}
		
		sb.append(numValue);
		
		return sb.toString();
	}

}
