

package com.seomse.commons.utils;
/**
 * <pre>
 *  파 일 명 : CodeUtil.java
 *  설    명 : 코드체계 관련 유틸성 클래스
 *
 *  작 성 자 : macle
 *  작 성 일 : 2018.03
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2018 by ㈜섬세한사람들. All right reserved.
 */
public class CodeUtil {
	
	/**
	 * 자리수만큼 00문자열을 생성해서 돌려줌
	 * @param codeNum codeNum
	 * @param length length
	 * @return CodeNumberValue
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

	
	
//	public static void main(String [] args){
//		
//		System.out.println(getCodeNumberValue(15,3));
//		
//		
//	}
	
	
}
