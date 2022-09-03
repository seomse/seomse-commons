package com.seomse.commons.service.memory;
/**
 * 메모리 저장중인 정보
 * @author macle
 */
public interface MemorySave {

    String getId();

    /**
     * @return 메모리 저장시간 초과 여부
     */
    boolean isTimeOut();

    void clear();
}
