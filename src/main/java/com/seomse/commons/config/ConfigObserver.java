
package com.seomse.commons.config;

import java.util.Map;
/**
 * <pre>
 *  파 일 명 : ConfigObserver.java
 *  설    명 : 설정정보 업데이트 감시
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.07
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */

public interface ConfigObserver {
	/**
	 * 설정정보 변경정보
	 * @param configMap 설정정보 맵
	 */
	void updateConfig(Map<String,String> configMap);
}