

package com.seomse.commons.utils.collection;

import java.util.Collection;
/**
 * <pre>
 *  파 일 명 : CollectionUtil.java
 *  설    명 : Collection 관련 구현체를 사용할떄의 유틸성 내용
 *
 *  작 성 자 : macle
 *  작 성 일 : 2018.03
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2018 by ㈜섬세한사람들. All right reserved.
 */

public class CollectionUtil {
	/**
	 * 동등비교 null 인식
	 * @param obj Collection
	 * @param obj2 Collection
	 * @return equals
	 */
	@SuppressWarnings("rawtypes") 
	public static boolean equals(Collection obj , Collection obj2) {
		
		if(obj  == null && obj2 == null) {
			return true;
		}
		
		if(obj == null) {
			return false;
		}
		
		
		if(obj2 == null) {
			return false;
		}

		return obj.equals(obj2);
	}
}
