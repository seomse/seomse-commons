/*
 * Copyright (C) 2020 Seomse Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seomse.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.slf4j.Logger;

import com.seomse.commons.handler.ExceptionHandler;

/**
 * 예외처리 관련 유틸
 * @author macle
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