

package com.seomse.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.slf4j.Logger;

import com.seomse.commons.handler.ExceptionHandler;
/**
 * <pre>
 *  파 일 명 : ExceptionUtil.java
 *  설    명 : Exception을 사용할때 공통으로 사용할만한 메소드들을 정의
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.07
 *  버    전 : 1.1
 *  수정이력 :  2018.04
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017, 2018 by ㈜섬세한사람들. All right reserved.
 */
public class ExceptionUtil {
	/**
	 *  Exception.printStackTrace 내용을 String 으로 가져오기
	 * @param e Exception e
	 * @return stackTrace String Value
	 */
	public static  String getStackTrace(Exception e){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
    	PrintStream printStream = new PrintStream(out);
    	e.printStackTrace(printStream);
    	String exceptionStackTrace = out.toString();

		//noinspection CatchMayIgnoreException
		try{out.close();}catch(Exception e1){}
		//noinspection CatchMayIgnoreException
        try{printStream.close();}catch(Exception e1){}
        
        return exceptionStackTrace;
	}
	
	/**
	 * 기본 예외처리
	 * @param e exception
	 * @param logger logger
	 * @param exceptionHandler exceptionHandler
	 */
	public static  void exception(Exception e, Logger logger, ExceptionHandler exceptionHandler){
		if(exceptionHandler == null) {
			logger.error(getStackTrace(e));
		}else{
			exceptionHandler.exception(e);
		}
	}
	
}