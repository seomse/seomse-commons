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
 *
 * 설정 변경 정보
 * @author macle
 */
public class ConfigInfo {
	String key;
	String value ;
	boolean isDelete = false;
	/**
	 * 생성자
	 * @param key String 설정 키
	 * @param value String 설정 값
	 */
	public ConfigInfo(String key, String value){
		this.key = key;
		this.value = value;
	}

	/**
	 * 생성자
	 */
	public ConfigInfo(){

	}

	/**
	 * 데이터 삭제
	 */
	public void setDelete(){
		isDelete = true;
	}

	/**
	 * 설정 키 얻기
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 키설정
	 * @param key String
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 값 얻기
	 * @return String
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 값 설정
	 * @param value String
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 삭제 여부
	 * @return boolean
	 */
	public boolean isDelete() {
		return isDelete;
	}
}
