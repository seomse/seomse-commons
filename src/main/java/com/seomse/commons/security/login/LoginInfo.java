/** 
 * <pre>
 *  파 일 명 : LoginInfo.java
 *  설    명 : 로그인관련 필요정보 
 *         
 *  작 성 자 : macle
 *  작 성 일 : 2017.07
 *  버    전 : 1.0
 *  수정이력 : 
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */

package com.seomse.commons.security.login;

public class LoginInfo {
	
	private String id;
	private String password;
	/**
	 * 생성자
	 */
	LoginInfo(String id, String password){
		this.id = id;
		this.password = password;
	}
	
	/**
	 * id 얻기
	 * @return
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * password 얻기
	 * @return
	 */
	public String getPassword(){
		return password;
	}
	
	
	
}
