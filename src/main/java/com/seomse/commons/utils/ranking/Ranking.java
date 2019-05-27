
package com.seomse.commons.utils.ranking;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.seomse.commons.utils.sort.QuickSortList;
/**
 * <pre>
 *  파 일 명 : Ranking.java
 *  설    명 : 순위 측정
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.11
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */

public class Ranking {
	
	/**
	 * 
	 * @param rankingInfoArray
	 * @param rankNumber
	 * @return
	 */
	public static <T extends RankingInfo> List<T> getRankingList(T [] rankingInfoArray, int rankNumber){
	
		
		if(rankingInfoArray.length == 0 ){
//			throw new RuntimeException("rankingInfoArray.length == 0 ");
			return Collections.emptyList();
		}
		
		if( rankNumber < 1){
			throw new RuntimeException("rankNumber < 1 rankNumber: " + rankNumber);
		}
		List<T> rankList = new LinkedList<T>();
		if(rankingInfoArray.length == 1){
			rankList.add(rankingInfoArray[0]);
			rankingInfoArray[0].setRanking(1);
			return rankList;
		}
		
		if(rankingInfoArray.length <= rankNumber){
			for(T info : rankingInfoArray){
				rankList.add(info);
			}
			
			sortAndRanking(rankList);
			
			return rankList;
		}
		
		
		for(int i=0 ; i<rankNumber ; i++){
			rankList.add(rankingInfoArray[i]);
		}
		sortAndRanking(rankList);
		
		
		for(int i=rankNumber ; i<rankingInfoArray.length ; i++){
			T info = rankingInfoArray[i];
			
			T last = rankList.get(rankList.size()-1);
			if(last.getRankingPoint() > info.getRankingPoint()){
				//비교할 필요가 없어요~~
				continue;
			}
			
			if(last.getRankingPoint() == info.getRankingPoint()){
				//점수가 같을경우
				info.setRanking(last.getRanking());
				rankList.add(info);
				continue;
			}
			
			
			
			//새로들어온게 기존것보다 크다.... 어디에 껴놓을지 찾기
			int startIndex= 0;
			int lastIndex = rankList.size();
			setRanking(rankList, rankNumber, info, startIndex, lastIndex);
		}
		
		return rankList;
	}
	
	private static <T extends RankingInfo> void setRanking(List<T> rankList,  int rankNumber, T info, int startIndex, int lastIndex){
		int gap = lastIndex - startIndex;
		if(gap < 7){
			
			for(int i=startIndex ; i<lastIndex ; i++){
				T compare = rankList.get(i);
				if(compare.getRankingPoint() < info.getRankingPoint()){
					info.setRanking(compare.getRanking());
					rankList.add(i, info);
					cutList(rankList, rankNumber, i);
					return;
				}
				
				if(compare.getRankingPoint() == info.getRankingPoint()){
					info.setRanking(compare.getRanking());
					int next = i+1;
					rankList.add(next, info);
					cutList(rankList, rankNumber, next);
					return;
				}
			}
			T compare = rankList.get(lastIndex-1);;
			int next = lastIndex;
			info.setRanking(compare.getRanking());
			rankList.add(next, info);
			cutList(rankList, rankNumber, next);
			return;
			
	
		}
		
		//중간
		int mid = startIndex + gap/2;
		T compare = rankList.get(mid);
		if(compare.getRankingPoint() < info.getRankingPoint()){
			//점수가 더클때
			setRanking(rankList, rankNumber, info, startIndex, mid);
			return;
		}
		
		if(compare.getRankingPoint() == info.getRankingPoint()){
			//점수가 같을때 이럴수가... 딱맞아
			info.setRanking(compare.getRanking());
			int next = mid+1;
			rankList.add(next, info);
			cutList(rankList, rankNumber, next);
			return;
		}
		
		
		//점수가 작을때
		
		setRanking(rankList, rankNumber, info, mid, lastIndex);
	}
		
	
	private static <T extends RankingInfo> void cutList(List<T> rankList, int rankNumber, int index ){
		int size = rankList.size();
		
		for(int i=index + 1; i<size ; i++){
			T last = rankList.get(i-1);
			T info = rankList.get(i);
			if(last.getRankingPoint() == info.getRankingPoint()){
				info.setRanking(last.getRanking());
			}else{
				info.setRanking(i+1);	
			}
		}
		
		if(rankList.size() >= rankNumber){
			
			for(int i=rankNumber; i<size ; i++){
				T last = rankList.get(i-1);
				T info = rankList.get(i);
				if(last.getRanking() == info.getRankingPoint()){
					continue;
				}
				
				//인덱스 아래로 전부삭제
				int compareSize = i;
				while(true){
					if(rankList.size() ==compareSize){
						break;
					}
					rankList.remove(rankList.size()-1);
				}
				break;
			}
		}
		
	}
	
	
	
	private static <T extends RankingInfo> void sortAndRanking(List<T> rankList){
		double [] pointArray = new double [rankList.size()];
		
		for(int i=0 ;i<pointArray.length ; i++){
			pointArray[i] = rankList.get(i).getRankingPoint();
		}
		
		new QuickSortList(rankList, pointArray , false);
				
		T first = rankList.get(0);
		first.setRanking(1);
		
		for(int i=1 ; i<pointArray.length ; i++){
			T last = rankList.get(i-1);
			T info = rankList.get(i);
			if(last.getRankingPoint() == info.getRankingPoint()){
				info.setRanking(last.getRanking());
			}else{
				info.setRanking(i+1);	
			}
		}
	}

}
