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
package com.seomse.commons.utils.time;

/**
 * 실행 시간 측정
 * @author macle
 */
public class RunningTime {
	
	private long startTime;
	private long lastTime = -1;

	/**
	 * 생성자
	 */
	public RunningTime(){
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * 작업 시작 타임을 가져온다.
	 * @return long 작업시작시간
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * 작업 시작 타임을 설정한다. 
	 * @param startTime long 작업 시작시간
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * 시작시간을 현제시간으로 설정한다.
	 */
	public void setStartTime() {
		this.startTime = System.currentTimeMillis();
	}
	
	
	/**
	 * 종료시간을 현제시간으로 설정한다.
	 */
	public void setLastTime() {
		this.lastTime = System.currentTimeMillis();
	}
	/**
	 * 작업 종료 타임을 얻어온다
	 * @return long LastTime
	 */
	public long getLastTime() {
		if(lastTime == -1){
			lastTime = System.currentTimeMillis();
		}
		return lastTime;
	}

	/**
	 * 작업 종료 타임을 설정한다
	 * @param lastTime long 작업 종료 타임
	 */
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	
	/**
	 * 작업시간을 얻어온다(밀리 세컨드)
	 * @return long 작업시간
	 */
	public long getRunningTime(){
		lastTime = System.currentTimeMillis();
		
		return lastTime - startTime;
	}


	/**
	 * 작업시간을 얻어온다 (초단위)0
	 * @return long 작업시간(초)
	 */
	public long getRunningSecond(){
		return (long)TimeUtil.getSecond(getRunningTime());
	}
	
	/**
	 * 작업시간을 얻어온다 (분단위)
	 * @return double 작업시간(분)
	 */
	public double getRunningMinute(){
		return TimeUtil.getMinute(getRunningTime());
	}

	/**
	 * 작업시간을 얻어온다 (시간단위)
	 * @return double 작업시간(시간)
	 */
	public double getRunningHour(){
		return TimeUtil.getHour(getRunningTime());
	}
	
	
}
