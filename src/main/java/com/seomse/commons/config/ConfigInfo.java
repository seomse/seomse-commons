
package com.seomse.commons.config;
/**
 * <pre>
 *  파 일 명 : ConfigInfo.java
 *  설    명 : 설정정보 클래스
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.05.28
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class ConfigInfo {
	String key;
	String value ;
	boolean isDelete = false;
	/**
	 * 생성자
	 * @param key 설정 키
	 * @param value 설정 값
	 */
	public ConfigInfo(String key, String value){
		this.key = key;
		this.value = value;
	}

	public ConfigInfo(){

	}

	/**
	 * 데이터 삭제
	 */
	public void setDelete(){
		isDelete = true;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isDelete() {
		return isDelete;
	}
}
