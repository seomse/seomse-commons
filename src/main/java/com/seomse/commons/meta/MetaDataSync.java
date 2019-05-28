package com.seomse.commons.meta;
/**
 * <pre>
 *  파 일 명 : MetaDataSync.java
 *  설    명 : 메타 데이터 동기화
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
public interface MetaDataSync {
    /**
     * 초기에 처음 실행될 이벤트 정의
     */
    void init();


    /**
     * 데이터 변경이 일어날때 밸상하는 이벤트 정의
     */
    void update();


}
