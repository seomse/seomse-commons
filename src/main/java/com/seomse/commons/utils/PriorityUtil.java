package com.seomse.commons.utils;

import com.seomse.commons.annotation.Priority;

/**
 * <pre>
 *  파 일 명 : PriorityUtil.java
 *  설    명 : 우선순위 유틸
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.05.29
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class PriorityUtil {


    /**
     * 우선순위 순서 값 얻기
     * @param classes classes
     * @return
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
