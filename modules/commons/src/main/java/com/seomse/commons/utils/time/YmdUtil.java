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

import com.seomse.commons.exception.ParseRuntimeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

/**
 * 년월일 yyyyMMdd 형태를 사용 할때의 유틸성 내용 정리
 * @author macle
 */
public class YmdUtil {

	/**
	 * 시작일부터 끝일까지의 날짜리스트값 얻기
	 *
	 * @param startYmd String yyyyMMdd
	 * @param endYmd   String yyyyMMdd
	 * @return List yyyyMMdd list
	 */
	public static List<String> getYmdList(String startYmd, String endYmd) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		List<String> dayList = new ArrayList<>();

		try {
			Date startDate = simpleDateFormat.parse(startYmd);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);


			int endNum = Integer.parseInt(endYmd);
			while (true) {


				String day = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());

				int dayNum = Integer.parseInt(day);
				if (dayNum > endNum) {
					break;
				}
				dayList.add(day);
				calendar.add(Calendar.DATE, 1);
			}
		} catch (ParseException e) {
			throw new ParseRuntimeException(e);
		}
		return dayList;
	}


	public static String getYmd(String ymd, int day) {
		if (day == 0) {
			return ymd;
		}

		return getYmd(ymd, day, null);
	}
	/**
	 * ymd 얻기
	 *
	 * @param ymd String yyyyMMdd
	 * @param day int day
	 * @return String yyyyMMdd
	 */
	public static String getYmd(String ymd, int day, ZoneId zoneId) {
		if (day == 0) {
			return ymd;
		}

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

			if(zoneId != null){
				simpleDateFormat.setTimeZone(TimeZone.getTimeZone(zoneId));
			}

			Date date = simpleDateFormat.parse(ymd);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, day);

			if(zoneId != null){
				TimeZone timeZone = TimeZone.getTimeZone(zoneId);
				calendar.setTimeZone(timeZone);

				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				format.setTimeZone(TimeZone.getTimeZone(zoneId));

				return format.format(calendar.getTime());
			}


			return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
		} catch (ParseException e) {
			throw new ParseRuntimeException(e);
		}

	}

	/**
	 * time 얻기
	 * @param ymd String yyyyMMdd
	 * @return long (unix time)
	 */
	public static long getTime(String ymd) {
		try {
			return new SimpleDateFormat("yyyyMMdd").parse(ymd).getTime();
		} catch (ParseException e) {
			throw new ParseRuntimeException(e);
		}
	}

	public static long getTime(int ymd, ZoneId zoneId) {
		return getTime(Integer.toString(ymd), zoneId);
	}

	public static long getTime(String ymd, ZoneId zoneId) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			format.setTimeZone(TimeZone.getTimeZone(zoneId));
			return format.parse(ymd).getTime();
		} catch (ParseException e) {
			throw new ParseRuntimeException(e);
		}
	}

	/**
	 * Date 얻기
	 * @param ymd String yyyyMMdd
	 * @return Date
	 */
	public static Date getDate(String ymd) {
		try {
			return new SimpleDateFormat("yyyyMMdd").parse(ymd);
		} catch (ParseException e) {
			throw new ParseRuntimeException(e);
		}
	}


	public static Date getDate(String ymd, ZoneId zoneId) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			format.setTimeZone(TimeZone.getTimeZone(zoneId));
			return format.parse(ymd);
		} catch (ParseException e) {
			throw new ParseRuntimeException(e);
		}
	}

	/**
	 * 오늘날자의 ymd 얻기
	 * @return String yyyyMMdd
	 */
	public static String now(){
		return getYmd(new Date());
	}

	public static String now(ZoneId zoneId){
		return getYmd(new Date(), zoneId);
	}


	/**
	 * ymd 얻기
	 * @param time unix time
	 * @return String yyyyMMdd
	 */
	public static String getYmd(long time) {
		return getYmd(new Date(time));
	}
	public static String getYmd(long time, ZoneId zoneId) {
		return getYmd(new Date(time), zoneId);
	}

	public static int getYmdInt(long time, ZoneId zoneId){
		return Integer.parseInt(getYmd(new Date(time), zoneId));
	}

	public static int getYmdInt(int ymd, int day){
		if(day == 0){
			return ymd;
		}

		return Integer.parseInt(getYmd(Integer.toString(ymd) ,day));
	}

	/**
	 * ymd 얻기
	 * @param date date
	 * @return String yyyyMMdd
	 */
	public static String getYmd(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	public static String getYmd(Date date, ZoneId zoneId) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		format.setTimeZone(TimeZone.getTimeZone(zoneId));
		return format.format(date);
	}


	public static int compare(String sourceYmd, String targetYmd){
		int source = Integer.parseInt(sourceYmd);
		int target = Integer.parseInt(targetYmd);

		return Integer.compare(source, target);

	}

	public static List<StartEndYmd> getYmdRangeList(String startYmd, String endYmd, int rangeDay){

		if(rangeDay < 0){
			throw new IllegalArgumentException("range day error (range day >= 0): " + rangeDay );
		}

		List<StartEndYmd> list = new ArrayList<>();

		String nextStartYmd = startYmd;

		for(;;){
			String nextEndYmd = getYmd(nextStartYmd, rangeDay);
			if(compare(nextEndYmd, endYmd) >= 0){
				list.add(new StartEndYmd(nextStartYmd, endYmd));
				break;
			}

			list.add(new StartEndYmd(nextStartYmd, nextEndYmd));
			nextStartYmd = getYmd(nextEndYmd,1);
		}


		return list;
	}


	public static boolean isNow(String ymd, ZoneId zoneId){
		String nowYmd = now(zoneId);
		return nowYmd.equals(ymd);
	}

	public static void main(String[] args) {
		List<StartEndYmd> list = getYmdRangeList("20000101", "20230130" , 29);
		for(StartEndYmd rangeYmd : list){
			System.out.println(rangeYmd);
		}
	}


}
