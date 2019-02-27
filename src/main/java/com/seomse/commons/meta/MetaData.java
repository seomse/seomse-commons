/** 
 * <pre>
 *  파 일 명 : MetaData.java
 *  설    명 : 메타데이터 업데이트 (메모리데이터 변경용)
 *         
 *  작 성 자 : macle
 *  작 성 일 : 2017.07
 *  버    전 : 1.0
 *  수정이력 : 
 *  기타사항 :
 * </pre>
 * @atuhor Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */

package com.seomse.commons.meta;

public interface MetaData {
	/**
	 * 메타 데이터 업데이트
	 * DB테이블의 변경내역등을 가져와서 업데이트 
	 */
	void update();

	/**
	 * 이름얻기
	 * @return
	 */
	String getName();
}
