

package com.seomse.commons.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * <pre>
 *  파 일 명 : YmdUtil.java
 *  설    명 : 년월일 형태의 날짜활용 유틸성클래스
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

public class YmdUtil {
	
	/**
	 * 시작일부터 끝일까지의 날짜리스트값 얻기
	 * @param startYmd startYmd
	 * @return YmdList
	 */
	public static List<String> getYmdList (String startYmd, String endYmd) throws ParseException{
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		
		Date startDate = simpleDateFormat.parse(startYmd);
		
		List<String> dayList = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		int endNum = Integer.parseInt(endYmd);
		while(true){
			
			
			String day = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
			
			int dayNum = Integer.parseInt(day);	
			if(dayNum > endNum){
				break;
			}
			dayList.add(day);
			calendar.add(Calendar.DATE, 1);
		}
		
		return dayList;
	}
	
	/**
	 * ymd 얻기
	 * @param ymd ymd
	 * @param day day
	 * @return ymd
	 */
	public static String getYmd(String ymd, int day) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		
		Date date = simpleDateFormat.parse(ymd);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		
		return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
		
	}

}	
