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

/**
 * 시간별 트랜드
 * @author macle
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
