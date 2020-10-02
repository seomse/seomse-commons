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
package com.seomse.commons.utils.collection;

import java.util.Collection;
/**
 * java.util.Collection 패지키 유틸성 메소드
 *
 * @author macle
 */
public class CollectionUtil {
	/**
	 * 동등비교 null 인식
	 * @param obj Collection
	 * @param obj2 Collection
	 * @return boolean equals
	 */
	@SuppressWarnings("rawtypes") 
	public static boolean equals(Collection obj , Collection obj2) {
		
		if(obj  == null && obj2 == null) {
			return true;
		}
		
		if(obj == null) {
			return false;
		}
		
		
		if(obj2 == null) {
			return false;
		}

		return obj.equals(obj2);
	}
}
