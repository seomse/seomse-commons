/** 
 * <pre>
 *  파 일 명 : ConfigInfo.java
 *  설    명 : 설정정보 클래스
 *  		  
 *  			                  
 *  작 성 자 : macle
 *  작 성 일 : 2017.07
 *  버    전 : 1.0
 *  수정이력 :  
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */
package com.seomse.commons.config;

public class ConfigInfo {
	private String key;
	private String value ;
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
	
}
