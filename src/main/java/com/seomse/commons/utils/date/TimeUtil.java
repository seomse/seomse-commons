

package com.seomse.commons.utils.date;
/**
 * <pre>
 *  파 일 명 : TimeUtil.java
 *  설    명 : 시간관련 유틸성클래스
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.10
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */
public class TimeUtil {
	
	//일분 밀리세컨드 값
	private static final int minuteTime = 60000;
	
	//한시간 밀리세컨드 값
	private static final int hourTime = 3600000;
	private static final int dayTime = 86400000;
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

		long day = milleSecond/ dayTime;
		
		milleSecond = milleSecond - dayTime *day;
		
		
		int hour = (int)milleSecond/hourTime;
		
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
	
