/** 
 * <pre>
 *  파 일 명 : ConfigConnection.java
 *  설    명 : 설정 정보 업데이트를 위한 컨넥션
 *         
 *  작 성 자 : macle
 *  작 성 일 : 2017.07
 *  버    전 : 1.0
 *  수정이력 : 
 *  기타사항 :
 * </pre>
 * @atuhor Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */
package com.seomse.commons.config;

import java.sql.Connection;

public interface ConfigConnection {

	/**
	 * 연결객체얻기
	 * @return
	 */
	Connection getConnection();
	
}
