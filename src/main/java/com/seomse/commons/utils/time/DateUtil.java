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
package com.seomse.commons.utils.time;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seomse.commons.utils.ExceptionUtil;

/**
 * Date 관련 유틸성 메소드
 *
 * @author yh.heo
 */
public class DateUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	
	
	public final static String DEFAULT_DATE_FORMATTER = "yyyyMMddHHmmss";
	
	/**
	 * long형 날짜를 문자열로 변환 시켜주는 유팉
	 * @param currTime long 기준 시간
	 * @return String default yyyyMMddHHmmss
	 */
	public static String getDateYmd(long currTime){
		return getDateYmd(currTime , DEFAULT_DATE_FORMATTER);
	}
	
	/**
	 * long형 날짜를 문자열로 변환 시켜주는 유팉
	 * @param currTime long 기준시간
	 * @param dateFormatter String 날짜포맷 문자열 ex) yyyyMMdd
	 * @return String formatter value
	 */
	public static String getDateYmd(long currTime , String dateFormatter){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatter);
		return sdf.format(new Date(currTime));
	}
	
	/**
	 * 날짜 문자열을 long형 시간으로 바꿔주는 유팉
	 * @param currTime String 기준시간
	 * @return long unix time
	 */
	public static long getDateTime(String currTime){
		return getDateTime(currTime,DEFAULT_DATE_FORMATTER); 
	}
	
	/**
	 * 날짜 문자열을 long형 시간으로 바꿔주는 유팉
	 * @param currTime String time text
	 * @param dateFormatter String 날짜포맷 문자열 ex) yyyyMMdd
	 * @return long unix time
	 */
	public static long getDateTime(String currTime , String dateFormatter){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatter);
		long result=-1L;
		try {
			result = (sdf.parse(currTime)).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(ExceptionUtil.getStackTrace(e));
		}
		return result;
	}
	/**
	 * 문자열 데이터에 대한 더하기 연산을 담당한다.
	 * @param currTime String 기준시간
	 * @param calendarType int Calandar 날짜타입 (예시)Calandar.HOUR
	 * @param addTime int add 시간 (예시) -3
	 * @param dateFormatter  String yyyyMMddHHmmss
	 * @return String formatter value
	 */
	public static String addDateYmd(String currTime , int calendarType , int addTime , String dateFormatter ){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( new Date( getDateTime(currTime , dateFormatter) ));
		calendar.add(calendarType, addTime); // 3개월 치 시간 데이터 계산
		return DateUtil.getDateYmd(calendar.getTime().getTime() , dateFormatter);
		
	}
	/**
	 * 문자열 데이터에 대한 더하기 연산을 담당한다.
	 * @param currTime String 기준시간 기본 : yyyyMMddHHmmss
	 * @param calendarType int Calandar 날짜타입 (예시)Calandar.HOUR
	 * @param addTime int add 시간 (예시) -3
	 * @return String default yyyyMMddHHmmss
	 */
	public static String addDateYmd(String currTime , int calendarType , int addTime  ){
		return addDateYmd(currTime, calendarType, addTime , DEFAULT_DATE_FORMATTER);
	}
	
	/**
	 * 파싱가능한 날짜포맷인지 체크한다.
	 * @param dateFormatter String yyyyMMddHHmmss
	 * @param value String value
	 * @return boolean
	 */
	public static boolean isValidDateFormat(String dateFormatter, String value) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormatter);
			date = sdf.parse(value);
			if (!value.equals(sdf.format(date))) {
				date = null;
			}
		} catch (ParseException e) {
			logger.error(ExceptionUtil.getStackTrace(e));
		}
		return date != null;
	}
}