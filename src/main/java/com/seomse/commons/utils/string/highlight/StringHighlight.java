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

import com.seomse.commons.data.StartEnd;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 문자열 하이라이트
 * @author macle
 */
public class StringHighlight {

    /**
     * Highlight 문자열 생성
     * @param text String
     * @param tokens String []
     * @param tokenIndexes int []
     * @param splitStartEnds StartEnd []
     * @param keywords String []
     * @param pre String
     * @param post String
     * @param length int
     * @return String
     */
    public static String make(String text, String [] tokens, int [] tokenIndexes, StartEnd [] splitStartEnds, String [] keywords, String pre, String post, int length ){

        //글자 길이수 역순으로 정렬
        Arrays.sort(keywords, (a, b)->Integer.compare(b.length(), a.length()));


        HighlightSearch max = null;
        HighlightSearch check =  null;
        outer:
        for (int i = 0; i <tokens.length ; i++) {
            String token = tokens[i];
            for (int jj = 0; jj <keywords.length ; jj++) {
                String keyword = keywords[jj];

                if(
                        token.equals(keyword)
                                || ( keyword.length() > 1 && token.startsWith(keyword) )
                                || ( keyword.length() > 1 && token.endsWith(keyword) )
                ){

                    HighlightKeyword highlightKeyword = new HighlightKeyword();
                    highlightKeyword.index = jj;
                    highlightKeyword.start = tokenIndexes[i];
                    highlightKeyword.end = tokenIndexes[i]+keyword.length();



                    if(check == null
                            || check.list.get(0).start + length <  highlightKeyword.end
                    ){
                        HighlightSearch search = new HighlightSearch();
                        search.list.add(highlightKeyword);
                        search.keywordIndexSet.add(jj);

                        check = search;

                        if(max == null){
                            max = search;
                        }

                    }else{
                        check.list.add(highlightKeyword);
                        check.keywordIndexSet.add(jj);

                        if(
                                max.keywordIndexSet.size() < check.keywordIndexSet.size()
                                        || (max.keywordIndexSet.size() == check.keywordIndexSet.size() && max.list.size() < check.list.size())
                        ){
                            max = check;
                        }

                    }

                    if(max.keywordIndexSet.size() == keywords.length){
                        break outer;
                    }



                    break;
                }
            }
        }

        if(max == null){
            if(length > text.length()){
                return text;
            }else{
                return text.substring(0, length);
            }
        }

        int highlightStart;
        int highlightEnd;

        if(text.length() < length){
            highlightStart = 0;
            highlightEnd = text.length();
        }else{
            highlightStart = max.list.get(0).start;
            highlightEnd = max.list.get(max.list.size()-1).end;

            int gap = length - (highlightEnd - highlightStart);


            //범위확장 가능여부 체크
            int startSplitIndex = 0;
            int splitStart = -1;

            for (int i = 0; i < splitStartEnds.length ; i++) {
                StartEnd startEnd = splitStartEnds[i];
                if(startEnd.getStart() <= highlightStart && startEnd.getEnd() > highlightStart){
                    splitStart = startEnd.getStart();
                    startSplitIndex = i;
                    break;
                }
            }

            if(splitStart == -1){
                throw new RuntimeException("splitStartEnds error");
            }

            //앞부분 확장
            if( highlightEnd - splitStart < length){
                highlightStart = splitStart;

            }else{
                int spaceIndex = text.lastIndexOf(' ', highlightStart);
                if(spaceIndex == -1){
                    if(highlightStart < gap){
                        highlightStart = 0;
                    }
                }else{

                    int checkIndex = spaceIndex+1;

                    if(checkIndex != highlightStart && startSplitIndex - checkIndex < gap){
                        highlightStart = checkIndex;
                    }
                }
            }
            
            //뒷 부분 확장
            gap = length - (highlightEnd - highlightStart);

            highlightEnd = Math.min(highlightEnd + gap, text.length());
        }

        HighlightKeyword [] highlightKeywords = max.list.toArray(new HighlightKeyword[0]);
        for(HighlightKeyword highlightKeyword : highlightKeywords){
            highlightKeyword.start -= highlightStart;
            highlightKeyword.end -= highlightStart;
        }



        if(highlightEnd == text.length()){
            return make (text.substring(highlightStart, highlightEnd), pre, post, highlightKeywords, false);
        }else{
            return make (text.substring(highlightStart, highlightEnd), pre, post, highlightKeywords, false) + "...";
        }
    }


    /***
     * Highlight 문자열 생성
     * @param text String
     * @param pre String
     * @param post String
     * @param startEnds StartEnd []
     * @return String
     */
    public static String make(String text, String pre, String post, StartEnd [] startEnds) {
        return make(text, pre, post, startEnds, true);
    }


    /***
     * Highlight 문자열 생성
     * @param text String
     * @param pre String
     * @param post String
     * @param startEnds StartEnd []
     * @param isSort boolean startEnds sort
     * @return String
     */
    public static String make(String text, String pre, String post, StartEnd [] startEnds, boolean isSort){

        if(isSort) {
            Arrays.sort(startEnds, Comparator.comparingInt(StartEnd::getStart));
        }
        StringBuilder sb = new StringBuilder();
        int lastIndex = 0;

        for(StartEnd startEnd : startEnds){
            sb.append(text, lastIndex, startEnd.getStart());
            sb.append(pre).append(text, startEnd.getStart(), startEnd.getEnd()).append(post);
            lastIndex = startEnd.getEnd();
        }

        if(lastIndex != text.length()){
            sb.append(text, lastIndex, text.length());
        }

        return sb.toString();
    }
}