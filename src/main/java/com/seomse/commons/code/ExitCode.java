
package com.seomse.commons.code;
/**
 * <pre>
 *  파 일 명 : ExitCode.java
 *  설    명 : 강제종료관련코드
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.09
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */
public enum ExitCode {
	 DB_SERVER_STOP(1001)
	, EXIT_ORDER(1002)
	, ERROR(-1)
	;
	private final int codeNum;
	ExitCode( int codeNum){
		this.codeNum = codeNum;
	}
	
	public int getCodeNum(){
		return codeNum;
	}
}