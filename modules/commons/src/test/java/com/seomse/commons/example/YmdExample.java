package com.seomse.commons.example;

import com.seomse.commons.utils.time.Times;
import com.seomse.commons.utils.time.YmdUtil;

import java.time.ZoneId;
import java.util.List;

/**
 * @author macle
 */
public class YmdExample {
    public static void main(String[] args) {
        System.out.println(YmdUtil.getYmd(System.currentTimeMillis()- Times.HOUR_10,  ZoneId.of("America/New_York")));
        System.out.println(YmdUtil.getYmd(System.currentTimeMillis()- Times.HOUR_10,  ZoneId.of("Asia/Seoul")));

        List<String> ymdList = YmdUtil.getYmdList("20220101", "20220801");
        for(String ymd : ymdList){
            System.out.println(ymd);
        }
    }

}
