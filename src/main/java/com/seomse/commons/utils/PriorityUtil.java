package com.seomse.commons.utils;

import com.seomse.commons.annotation.Priority;

import java.util.Comparator;

/**
 * 우선 순위 관련 유틸
 * @author macle
 */
public class PriorityUtil {


    public static final Comparator<Object> PRIORITY_SORT = (o1, o2) -> {
        int seq1 = PriorityUtil.getSeq(o1.getClass());
        int seq2 = PriorityUtil.getSeq(o2.getClass());
        return Integer.compare(seq1, seq2);
    };

    /**
     * 우선순위 순서 값 얻기
     * @param classes classes
     * @return int seq
     */
    public static int getSeq(Class<?> classes){
        int seq ;
        try{

            Priority priority = classes.getAnnotation(Priority.class);
            if(priority != null){
                seq = priority.seq();
            }else{
                seq = 1000;
            }



        }catch(Exception e){
            seq = 1000;
        }
        return seq;
    }



}
