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

package com.seomse.commons.utils.string.highlight;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 하이라이크 검색 정보
 * @author macle
 */
class HighlightSearch {

    List<HighlightKeyword> list = new ArrayList<>();
    Set<Integer> keywordIndexSet = new HashSet<>();

    /**
     * 중복체크
     * 기존에 가진 키워드와 중복된 위치가 있는지 체크한다
     * @param begin
     * @param end
     * @return 위치가 겹치는지 여부
     */
    boolean isOverlap(int begin, int end){

        int endIndex = end - 1;

        for(HighlightKeyword highlightKeyword : list ){

            // 하이라이트 기준으로 체크
            if(highlightKeyword.getBegin() <= begin && highlightKeyword.getEnd() > begin){
                //시작 부분이 겹치면
                return true;
            }
            if(highlightKeyword.getBegin() <= endIndex && highlightKeyword.getEnd() > endIndex){
                //끝 위치가 겹치면
                return true;
            }

            //새로 들어온 위치 기준으로 체크
            if(begin <= highlightKeyword.getBegin() && end > highlightKeyword.getBegin()){
                //시작 위치가 겹치면
                return true;
            }

            int highlightEndIndex = highlightKeyword.getEnd()-1;
            //새로 들어온 위치 기준으로 체크
            if(begin <= highlightEndIndex && end > highlightEndIndex){
                //끝 위치가 겹치면
                return true;
            }

        }


        return false;

    }

}
