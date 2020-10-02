/*
 * Copyright (C) 2020 Seomse Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seomse.commons.service;

import com.seomse.commons.callback.ObjCallback;
import com.seomse.commons.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 동작 서비스 단위의 추상 쿨래스
 *
 * @author macle
 */
public abstract class Service extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(Service.class);


    private String serviceId;

    /**
     * 서비스 아이디 설정
     * @param serviceId String
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * START : started
     * STAND : stand
     * STOP :  stop
     * ONE_OFF :
     * @author macle
     */
    public enum State{
        START
        , STAND
        , STOP
        , ONE_OFF
    }

    private boolean isStop = false;

    protected State state = State.STAND;

    /**
     * 상태설정 
     * @param state State
     */
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
    private ObjCallback endCallback = null;

    /**
     * 종료 객체 설정
     * @param endObject Object
     */
    public void setEndObject(Object endObject) {
        this.endObject = endObject;
    }

    /**
     * 종로 콜백 설정
     * endObject 가 전달죔
     * @param endCallback ObjCallback
     */
    public void setEndCallback(ObjCallback endCallback) {
        this.endCallback = endCallback;
    }

    //지연된 시작 (설정할 경우)
    private Long delayStartTime = null;

    //반복할때의 슬립 타임
    private Long sleepTime = null;

    /**
     * 지연 시작 시간 설정
     * @param delayTime Long delayTime
     */
    public void setDelayStartTime(Long delayTime) {
        this.delayStartTime = delayTime;
    }

    /**
     * 반복 대기 시간 설정
     * @param sleepTime Long sleepTime
     */
    public void setSleepTime(Long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void killService(){
        state = State.STOP;
        this.interrupt();
    }

    /**
     * 서비스가 종료 되었는지 확인
     * @return boolean is stop
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

        if(delayStartTime != null && delayStartTime > 0 ){
            try{
                Thread.sleep(delayStartTime);
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

                    if(sleepTime != null && state != State.STOP && sleepTime > 0){
                        //noinspection BusyWait
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
        if(endCallback!= null){
            endCallback.callback(endObject);
        }
        isStop = true;
        if(serviceId != null){
            ServiceManager.getInstance().removeService(serviceId);
        }
    }

    /**
     * 서비스 아이디 얻기
     * @return String
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 서비스 작업 내용 정의
     */
    public abstract void work();
}
