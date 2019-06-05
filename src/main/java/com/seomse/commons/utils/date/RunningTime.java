

package com.seomse.commons.utils.date;
/**
 * <pre>
 *  파 일 명 : RunningTime.java
 *  설    명 : running 시간 측정용 클래스
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.10
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */

public class RunningTime {
	
	private long startTime;
	private long lastTime = -1;
	
	public RunningTime(){
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * 작업 시작 타임을 가져온다.
	 * @return 작업시작시간
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * 작업 시작 타임을 설정한다. 
	 * @param startTime 작업 시작시간
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
	 * @return LastTime
	 */
	public long getLastTime() {
		if(lastTime == -1){
			lastTime = System.currentTimeMillis();
		}
		return lastTime;
	}

	/**
	 * 작업 종료 타임을 설정한다
	 * @param lastTime 작업 종료 타임
	 */
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	
	/**
	 * 작업시간을 얻어온다(밀리 세컨드)
	 * @return 작업시간
	 */
	public long getRunningTime(){
		lastTime = System.currentTimeMillis();
		
		return lastTime - startTime;
	}


	/**
	 * 작업시간을 얻어온다 (초단위)0
	 * @return 작업시간(초)
	 */
	public long getRunningSecond(){
		return (long)TimeUtil.getSecond(getRunningTime());
	}
	
	/**
	 * 작업시간을 얻어온다 (분단위)
	 * @return 작업시간(분)
	 */
	public double getRunningMinute(){
		return TimeUtil.getMinute(getRunningTime());
	}

	/**
	 * 작업시간을 얻어온다 (시간단위)
	 * @return 작업시간(시간)
	 */
	public double getRunningHour(){
		return TimeUtil.getHour(getRunningTime());
	}
	
	
}
