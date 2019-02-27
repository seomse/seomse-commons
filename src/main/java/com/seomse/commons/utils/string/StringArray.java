/** 
 * <pre>
 *  파 일 명 : StringArray.java
 *  설    명 : String[] 형태의 유틸성 클래스
 *                    
 *  작 성 자 : macle
 *  작 성 일 : 2017.09
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜ 모아라. All right reserved.
 */

package com.seomse.commons.utils.string;

import com.seomse.commons.utils.sort.QuickSortStringArray;

public class StringArray {
	public static final String [] EMPTY_STRING_ARRAY = new String[0];
	
	/**
	 * 스트링배열을 문자열 길이에 따라 정렬
	 * @param array
	 * @param sortNumArray
	 * @param isAsc
	 */
	public static void sortToLength(String [] array , boolean isAsc){
		
		if(array == null || array.length<2){
			return;
		}
		
		int [] sortNumArray = new int[array.length];
		for(int i=0 ; i<sortNumArray.length ; i++){
			sortNumArray[i] = array[i].length();
		}
		
		new QuickSortStringArray(array, sortNumArray, isAsc);
//		QuickSortStringArray sortStringArray = new QuickSortStringArray(array);
//		if(isAsc){
//			sortStringArray.sortAsc(sortNumArray);
//		}else{
//			sortStringArray.sortDesc(sortNumArray);	
//		}
	}
	
}