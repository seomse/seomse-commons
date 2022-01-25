package com.seomse.commons.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

/**
 * 평균 관련 유틸성 클래스
 * @author macle
 */
public class BigDecimalUtil {

    /**
     * 중간평균
     * @param valueArray 평균을 구하기 위한 배열
     * @param outRate 제외비율
     * @return 중간평균
     */
    public BigDecimal getAverage(BigDecimal [] valueArray, BigDecimal outRate){
        Arrays.sort(valueArray);
        int start = new BigDecimal(valueArray.length).multiply(outRate).intValue();
        int last = valueArray.length - start;
        BigDecimal sum = BigDecimal.ZERO;
        for(int i=start ; i < last ; i++){
            sum = sum.add(valueArray[i]);
        }
        return sum.divide(new BigDecimal(last - start), MathContext.DECIMAL128);
    }

    /**
     * 평균 얻기
     * @param valueArray double []
     * @return double 평균
     */
    public BigDecimal getAverage(BigDecimal [] valueArray){

        BigDecimal sum = BigDecimal.ZERO;
        for(BigDecimal value : valueArray){
            sum = sum.add(value);
        }
        return sum.divide(new BigDecimal(valueArray.length), MathContext.DECIMAL128);
    }


}
