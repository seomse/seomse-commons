/** 
 * <pre>
 *  파 일 명 : MetaDataManager.java
 *  설    명 : 메타데이터 관리자
 *         
 *  작 성 자 : macle
 *  작 성 일 : 2017.07
 *  버    전 : 1.0
 *  수정이력 : 
 *  기타사항 :
 * </pre>
 * @atuhor Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */

package com.seomse.commons.meta;

import java.util.LinkedList;
import java.util.List;

public class MetaDataManager {
	
	
	private static class Singleton {
		private static final MetaDataManager instance = new MetaDataManager();
	}

	/**
	 * 싱글인스턴스 얻기
	 * @return
	 */
	public static final MetaDataManager getInstance (){
		return Singleton.instance;
	}
	
	
	
	
	private List<MetaData> metaDataList = new LinkedList<MetaData>();
	
	private Object metaListLock = new Object();
	
	
	/**
	 * 생성자
	 */
	private MetaDataManager(){
	
	}
	
	
	/**
	 * 메타데이터 추가
	 * @param metaData
	 */
	public void addMetaData(MetaData metaData){
		synchronized (metaListLock) {
			if(metaDataList.contains(metaData)){
				return ;
			}
			metaDataList.add(metaData);
		}
	}

	/**
	 * 메타데이터 제거
	 * @param metaData
	 */
	public void removeMetaData(MetaData metaData){
		synchronized (metaListLock) {
			metaDataList.remove(metaData);
		}
	}
	
	/**
	 * 메타데이터 추가 변경
	 * 인덱스 정보
	 * @param index
	 * @param metaData
	 */
	public void addMetaData( int index, MetaData metaData){
		synchronized (metaListLock) {
			//기존정보제거
			metaDataList.remove(metaData);
			//새로운 위치에 다시저장
			if(index >= metaDataList.size()){
				metaDataList.add(metaData);
			}else{
				metaDataList.add(index, metaData);
			}
		}
	}
	
	
	

}
