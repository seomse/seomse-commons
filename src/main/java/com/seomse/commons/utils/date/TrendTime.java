package com.seomse.commons.utils.date;

/**
 * <pre>
 *  파 일 명 : TimeUtil.java
 *  설    명 : 시간관련 유틸성클래스
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2020.01.01
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2020 by ㈜섬세한사람들. All right reserved.
 */
public class TrendTime {


    public static int getIndex (long time, long [] trendTimes, long gap){

        int end  = trendTimes.length - 1;

        for (int i = 0; i < end ; i++) {
            if(trendTimes[i] == time){
                return i;
            }

            if(trendTimes[i] < time && trendTimes[i+1] > time){
                return i;
            }
        }

        if(trendTimes[end] == time){
            return end ;
        }

        if(trendTimes[end] < time && trendTimes[end] + gap > time){
            return end;
        }

        return -1;
    }

    /**
     * trend times set
     * @param standardTime 기준시간  long
     * @param gap 시간갭 long
     * @param trendCount 트랜트 개수 int
     * @return long []
     */
    public static long [] getTrendTimes(long standardTime, long gap, int trendCount ){
        long [] trendTimes = new long[trendCount];
        int gapIndex = 0;
        for (int i = trendTimes.length-1; i > -1; i--) {
            trendTimes[i] = standardTime - (gapIndex * gap);
            gapIndex++;
        }
        return trendTimes;
    }

}
