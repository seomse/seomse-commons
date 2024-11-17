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
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 * 트레이딩에서의 기준 시간정보
 * @author macle
 */
public class Times {


    //초정리
    public static final long SECOND_1 = 1000L;
    public static final long SECOND_3 = SECOND_1*3L;
    public static final long SECOND_5 = SECOND_1*5L;
    public static final long SECOND_10 = SECOND_1*10L;
    public static final long SECOND_15 = SECOND_1*15L;
    public static final long SECOND_20 = SECOND_1*20L;
    public static final long SECOND_30 = SECOND_1*30L;
    public static final long SECOND_40 = SECOND_1*40L;
    public static final long SECOND_50 = SECOND_1*50L;


    //분정리
    public static final long MINUTE_1 = 1000L*60L;
    public static final long MINUTE_2 = MINUTE_1 * 2L;
    public static final long MINUTE_3 = MINUTE_1 * 3L;
    public static final long MINUTE_4 = MINUTE_1 * 4L;
    public static final long MINUTE_5 = MINUTE_1 * 5L;
    public static final long MINUTE_6 = MINUTE_1 * 6L;
    public static final long MINUTE_7 = MINUTE_1 * 7L;
    public static final long MINUTE_8 = MINUTE_1 * 8L;
    public static final long MINUTE_9 = MINUTE_1 * 9L;

    public static final long MINUTE_10 = MINUTE_1 * 10L;
    public static final long MINUTE_11 = MINUTE_1 * 11L;

    public static final long MINUTE_12 = MINUTE_1 * 12L;
    public static final long MINUTE_13 = MINUTE_1 * 13L;
    public static final long MINUTE_14 = MINUTE_1 * 14L;
    public static final long MINUTE_15 = MINUTE_1 * 15L;

    public static final long MINUTE_20 = MINUTE_1 * 20L;
    public static final long MINUTE_25 = MINUTE_1 * 25L;

    public static final long MINUTE_30 = MINUTE_1 * 30L;

    public static final long MINUTE_40 = MINUTE_1 * 40L;
    public static final long MINUTE_50 = MINUTE_1 * 50L;


    //시간정리
    public static final long HOUR_1 = MINUTE_1 * 60L;
    public static final long HOUR_2 = HOUR_1 * 2L;
    public static final long HOUR_3 = HOUR_1 * 3L;
    public static final long HOUR_4 = HOUR_1 * 4L;
    public static final long HOUR_5 = HOUR_1 * 5L;
    public static final long HOUR_6 = HOUR_1 * 6L;
    public static final long HOUR_7 = HOUR_1 * 7L;
    public static final long HOUR_8 = HOUR_1 * 8L;
    public static final long HOUR_9 = HOUR_1 * 9L;
    public static final long HOUR_10 = HOUR_1 * 10L;
    public static final long HOUR_11 = HOUR_1 * 11L;
    public static final long HOUR_12 = HOUR_1 * 12L;

    public static final long HOUR_13 = HOUR_1 * 13L;
    public static final long HOUR_14 = HOUR_1 * 14L;
    public static final long HOUR_15 = HOUR_1 * 15L;
    public static final long HOUR_16 = HOUR_1 * 16L;
    public static final long HOUR_17 = HOUR_1 * 17L;
    public static final long HOUR_18 = HOUR_1 * 18L;
    public static final long HOUR_19 = HOUR_1 * 19L;
    public static final long HOUR_20 = HOUR_1 * 20L;
    public static final long HOUR_21 = HOUR_1 * 21L;
    public static final long HOUR_22 = HOUR_1 * 22L;
    public static final long HOUR_23 = HOUR_1 * 23L;


    //일정리
    public static final long DAY_1 = HOUR_1 * 24L;
    public static final long DAY_2= DAY_1 * 2L;
    public static final long DAY_3= DAY_1 * 3L;
    public static final long DAY_4= DAY_1 * 4L;
    public static final long DAY_5= DAY_1 * 5L;
    public static final long DAY_6= DAY_1 * 6L;
    public static final long DAY_7= DAY_1 * 7L;
    public static final long DAY_8= DAY_1 * 8L;
    public static final long DAY_9= DAY_1 * 9L;
    public static final long DAY_10= DAY_1 * 10L;
    public static final long DAY_11= DAY_1 * 11L;
    public static final long DAY_12= DAY_1 * 12L;
    public static final long DAY_13= DAY_1 * 13L;
    public static final long DAY_14= DAY_1 * 14L;
    public static final long DAY_15= DAY_1 * 15L;
    public static final long DAY_16= DAY_1 * 16L;
    public static final long DAY_17= DAY_1 * 17L;
    public static final long DAY_18= DAY_1 * 18L;
    public static final long DAY_19= DAY_1 * 19L;
    public static final long DAY_20= DAY_1 * 20L;
    public static final long DAY_21= DAY_1 * 21L;

    public static final long DAY_25= DAY_1 * 25L;
    public static final long DAY_28= DAY_1 * 28L;


    public static final long DAY_30= DAY_1 * 30L;
    public static final long DAY_31= DAY_1 * 31L;
    public static final long DAY_35= DAY_1 * 35L;
    public static final long DAY_50= DAY_1 * 50L;
    public static final long DAY_100= DAY_1 * 100L;

    //주
    public static final long WEEK_1= DAY_7;
    public static final long WEEK_2= WEEK_1 * 2L;
    public static final long WEEK_3= WEEK_1 * 3L;
    public static final long WEEK_4= WEEK_1 * 4L;
    public static final long WEEK_5= WEEK_1 * 5L;
    public static final long WEEK_6= WEEK_1 * 6L;
    public static final long WEEK_7= WEEK_1 * 7L;
    public static final long WEEK_8= WEEK_1 * 8L;
    public static final long WEEK_9= WEEK_1 * 9L;
    public static final long WEEK_10= WEEK_1 * 10L;
    public static final long WEEK_11= WEEK_1 * 11L;
    public static final long WEEK_12= WEEK_1 * 12L;
    public static final long WEEK_16= WEEK_1 * 16L;
    public static final long WEEK_20= WEEK_1 * 20L;
    public static final long WEEK_21= WEEK_1 * 21L;
    public static final long WEEK_52= WEEK_1 * 52L;


    /**
     * 년원일시분
     *
     * @param time unix time
     * @param zoneId example ZoneId.of("Asia/Seoul")
     * @return yyyyMMdd HHmm
     */
    public static String ymdhm(long time, ZoneId zoneId){
        Instant i = Instant.ofEpochMilli(time);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(i, zoneId);

        return zonedDateTime.getYear() + DateUtil.getDateText(zonedDateTime.getMonthValue()) + DateUtil.getDateText(zonedDateTime.getDayOfMonth()) + " " + DateUtil.getDateText(zonedDateTime.getHour()) + DateUtil.getDateText(zonedDateTime.getMinute()) ;
    }

    /**
     * 년월일시
     * @param time unix time
     * @param zoneId example ZoneId.of("Asia/Seoul")
     * @return yyyyMMddHH
     */
    public static String ymdh(long time, ZoneId zoneId){
        Instant intent = Instant.ofEpochMilli(time);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(intent, zoneId);
        return zonedDateTime.getYear() + DateUtil.getDateText(zonedDateTime.getMonthValue())
                + DateUtil.getDateText(zonedDateTime.getDayOfMonth()) + DateUtil.getDateText(zonedDateTime.getHour());
    }

    /**
     * 년월일
     * @param time unix time
     * @param zoneId example ZoneId.of("Asia/Seoul")
     * @return yyyyMMdd
     */
    public static String ymd(long time, ZoneId zoneId){
        Instant intent = Instant.ofEpochMilli(time);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(intent, zoneId);
        return zonedDateTime.getYear() + DateUtil.getDateText(zonedDateTime.getMonthValue())
                + DateUtil.getDateText(zonedDateTime.getDayOfMonth());
    }

    /**
     * 년월
     * @param time unix time
     * @param zoneId example ZoneId.of("Asia/Seoul")
     * @return yyyyMM
     */
    public static String ym(long time, ZoneId zoneId){
        Instant intent = Instant.ofEpochMilli(time);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(intent, zoneId);
        return zonedDateTime.getYear() + DateUtil.getDateText(zonedDateTime.getMonthValue())
                ;
    }

    /**
     * 년
     * @param time unix time
     * @param zoneId example ZoneId.of("Asia/Seoul")
     * @return yyyy
     */
    public static String year(long time, ZoneId zoneId){
        Instant intent = Instant.ofEpochMilli(time);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(intent, zoneId);
        return Integer.toString(zonedDateTime.getYear());
    }


    public static long getTime(String format, String timeText, ZoneId zoneId){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone(zoneId));
        try {
            return dateFormat.parse(timeText).getTime();
        } catch (ParseException e) {
            throw new ParseRuntimeException(e);
        }
    }

    
    
    public static long getTimeHm(String hhmm){
        hhmm = hhmm.trim();

        String hh = hhmm.substring(0,2);
        String mm = hhmm.substring(2);

        long time = Long.parseLong(hh) * Times.HOUR_1;
        time += Long.parseLong(mm)*Times.MINUTE_1;

        return time ;
    }


    public static DayOfWeek getDayOfWeek(long time, ZoneId zoneId){
        Instant i = Instant.ofEpochMilli(time);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(i, zoneId);

        return  zonedDateTime.getDayOfWeek();

    }

    public static String getYmd(String ymdhm){
        return ymdhm.substring(0,8);
    }
    public static String getHm(String ymdhm){
        return ymdhm.substring(9);
    }

    public static final String DATE_FORMAT_YMDHM = "yyyyMMdd HHmm";

    public static String getYmdhm(String ymdhm, long addTime){

        ZoneId zoneId = ZoneId.systemDefault();

        long time = getTime(DATE_FORMAT_YMDHM, ymdhm, zoneId);

        time = time + addTime;
        return ymdhm(time, zoneId);

    }


}
