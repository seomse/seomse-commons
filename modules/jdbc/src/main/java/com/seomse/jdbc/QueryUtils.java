package com.seomse.jdbc;
/**
 * 쿼리 관련 유틸모음
 * @author macle
 */
public class QueryUtils {

    /**
     * where in 질의에 들어갈 내용
     * @param inValues in에 들어갈 목록
     * @return query
     */
    public static String whereIn(String [] inValues){
        StringBuilder sb = new StringBuilder();
        for(String inValue : inValues){
            sb.append(",'").append(inValue).append("'");
        }

        return "(" + sb.substring(1) +")";

    }


}
