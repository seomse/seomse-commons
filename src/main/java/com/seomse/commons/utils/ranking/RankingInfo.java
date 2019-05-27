

package com.seomse.commons.utils.ranking;
/**
 * <pre>
 *  파 일 명 : RankingInfo.java
 *  설    명 : 순위 정보
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.11
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */
public interface RankingInfo {
	
	/**
	 * 측정된 랭킹 설정
	 * @param ranking
	 */
	void setRanking(int ranking);
	
	/**
	 * 측정된 순위 얻기
	 * @param
	 */
	int getRanking();
	
	/**
	 * 랭킹측정에 사용할 점수 얻기
	 * @return
	 */
	double getRankingPoint();
}
