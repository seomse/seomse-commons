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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * 년월일 yyyyMMdd 형태를 사용 할때의 유틸성 내용 정리
 * @author macle
 */
public class YmdUtil {

	/**
	 * 시작일부터 끝일까지의 날짜리스트값 얻기
	 * @param startYmd string yyyyMMdd
	 * @param endYmd string yyyyMMdd
	 * @return List yyyyMMdd list
	 */
	public static List<String> getYmdList (String startYmd, String endYmd){
		
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
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return dayList;
	}
	
	/**
	 * ymd 얻기
	 * @param ymd ymd
	 * @param day day
	 * @return ymd
	 */
	public static String getYmd(String ymd, int day) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

			Date date = simpleDateFormat.parse(ymd);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, day);

			return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}

}	
