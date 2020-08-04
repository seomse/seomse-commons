package com.seomse.commons.utils;

import com.seomse.commons.annotation.Priority;

import java.util.Comparator;

/**
 * <pre>
 *  파 일 명 : PriorityUtil.java
 *  설    명 : 우선순위 유틸
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.05.29
 *  버    전 : 1.1
 *  수정이력 : 2019.10.25
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
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
     * @return seq
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
