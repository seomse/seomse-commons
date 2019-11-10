package com.seomse.commons.service;

import com.seomse.commons.handler.EndHandler;
import com.seomse.commons.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  파 일 명 : Service.java
 *  설    명 : 서비스 형태로 동작하는 이벤트 정의
 *  작 성 자 : macle
 *  작 성 일 : 2019.11.11
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public abstract class Service extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(Service.class);


    private String serviceId;

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * START : 실행되고 있는 상태
     * STAND : 대기 상태
     * STOP : 서비스 중지
     * ONE_OFF : 반복이 아닌 일회성 시비스
     * @author wini
     */
    public enum State{
        START
        , STAND
        , STOP
        , ONE_OFF
    }

    private boolean isStop = false;

    protected State state = State.STAND;

    public void setState(State state) {
        this.state = state;
    }

    /**
     * 종료때 전달할 객체가 있을경우
     */
    private Object endObject = null;

    /**
     * 종료 핸들링
     */
    private EndHandler endHandler = null;

    public void setEndObject(Object endObject) {
        this.endObject = endObject;
    }

    public void setEndHandler(EndHandler endHandler) {
        this.endHandler = endHandler;
    }

    //지연된 시작 (설정할 경우)
    private Long delayTime = null;

    //반복할때의 슬립 타임
    private Long sleepTime = null;

    public void killService(){
        state = State.STOP;
        this.interrupt();
    }

    /**
     * 서비스가 종료되었는지 확인
     * @return is stop
     */
    public boolean isStop(){
        return isStop;
    }

    @Override
    public void run(){
        if(state == State.STOP){
            serviceStop();
            return;
        }

        if(serviceId != null){
            ServiceManager.getInstance().addService(this);
        }

        if(delayTime != null){
            try{
                Thread.sleep(delayTime);
            }catch(Exception e){
                logger.error(ExceptionUtil.getStackTrace(e));
                serviceStop();
                return;
            }
        }

        if(state == State.STOP){
            serviceStop();
            return;
        }

        if(state == State.ONE_OFF){
            try{
                work();
            }catch(Exception e){
                logger.error(ExceptionUtil.getStackTrace(e));
                serviceStop();
                return;
            }

        }else{
            try{
                while(state != State.STOP){
                    if(state == State.START){
                        work();
                    }

                    if(sleepTime != null){
                        Thread.sleep(sleepTime);
                    }
                }
            }catch(Exception e){
                logger.error(ExceptionUtil.getStackTrace(e));
                serviceStop();
                return;
            }
        }
        serviceStop();
    }

    private void serviceStop(){
        if(endHandler!= null){
            endHandler.end(endObject);
        }
        isStop = true;
        if(serviceId != null){
            ServiceManager.getInstance().removeService(serviceId);
        }
    }


    public String getServiceId() {
        return serviceId;
    }

    /**
     * 서비스 작업 내용 정의
     */
    public abstract void work();
}
