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

package com.seomse.commons.utils;

import java.util.Arrays;

/**
 * 평균 관련 유틸성 클래스
 * @author macle
 */
public class AverageUtil
{

	/**
	 * 분포평균 얻기 (10%[0.1] 를지정하면 상위10% 하위10%를 제거한 중간값들의 평균)
	 * @param valueArray double []
	 * @param percent double 0.1을 지정하면 상위10% 하위10%를 제거한 중간값들의 평균
	 * @return double 분포평균
	 */
	public static double getAverageDistribution(double [] valueArray, double percent){
		Arrays.sort(valueArray);
		int start = (int)(((double) valueArray.length) * percent);
		int last = valueArray.length - start;
		double sum = 0;
		for(int i=start ; i < last ; i++){
			sum += valueArray[i];
		}
		return sum/(double)(last - start);
	}

	/**
	 * 평균 얻기
	 * @param valueArray double []
	 * @return double 평균
	 */
	public static double getAverage(double [] valueArray){

		double sum = 0;
		for(double value : valueArray){
			sum += value;
		}
		return sum/valueArray.length;
	}

}
