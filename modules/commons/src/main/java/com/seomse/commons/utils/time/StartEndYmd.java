package com.seomse.commons.utils.time;

import lombok.Data;

/**
 * 국가별 업무시간
 * 크롤링에서 각가별 업무시간은 피해서 크롤링할 필요가 있을경우
 * @author macle
 */
@Data
public class StartEndYmd {
    String startYmd;
    String endYmd;

    public StartEndYmd(){}

    public StartEndYmd(String startYmd, String endYmd){
        this.startYmd = startYmd;
        this.endYmd = endYmd;
    }


    @Override
    public String toString(){
        return startYmd +"," + endYmd;
    }

}
