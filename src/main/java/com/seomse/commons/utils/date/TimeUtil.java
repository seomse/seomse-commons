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
package com.seomse.commons.utils.date;


/**
 * 시간 관련 유틸성 내용
 * @author macle
 */
public class TimeUtil {
	
	//일분 밀리세컨드 값
	private static final long minuteTime = 60000L;
	
	//한시간 밀리세컨드 값
	private static final long hourTime = 3600000L;
	private static final long dayTime = 86400000L;
	/**
	 * 초단위 값을 가져온다.
	 * @param milleSecond 천분의 1초 
	 * @return second
	 */
	public static double getSecond(long milleSecond){
		return (double)milleSecond/(double)1000;
	}
	

	/**
	 * 분단위 값을 가져온다.
	 * @return milleSecond 천분의 1초 
	 */
	public static double getMinute(long milleSecond){
		return (double)milleSecond/(double)minuteTime;
	}
	
	
	/**
	 * 시간단위 값으로 변환해서 가져온다.
	 * @param milleSecond 천분의 1초
	 * @return hour
	 */
	public static double getHour(long milleSecond){
		return (double)milleSecond/(double)hourTime;
	}
	
	/**
	 * 일,시간,분,초, 나머지 밀리세컨드 형태의 문자열로 가져온다.
	 * @param milleSecond 천분의 1초
	 * @return TimeValue
	 */
	public static String getTimeValue(long milleSecond){
		
			
		StringBuilder timeValueBuilder = new StringBuilder();

		//하루 밀리세컨드 값

		long day = milleSecond/dayTime;
		
		milleSecond = milleSecond - dayTime *day;
		
		
		int hour = (int)(milleSecond/hourTime);
		
		milleSecond = milleSecond - hourTime*hour;
		
		int minute = (int)(milleSecond/60000L);
		milleSecond = milleSecond - minuteTime*minute;
		
		double second = (double)milleSecond/1000.0;

		
		timeValueBuilder.append(day);
		timeValueBuilder.append("day");
		
		timeValueBuilder.append("  ").append(hour);
		timeValueBuilder.append("Hour");
		timeValueBuilder.append("  ").append(minute);
		timeValueBuilder.append("Minute");

		timeValueBuilder.append("  ").append(String.format("%.2f", second));
		timeValueBuilder.append("Second");
			
			
		return timeValueBuilder.toString();
	}

}
	
