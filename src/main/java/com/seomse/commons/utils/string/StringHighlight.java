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

package com.seomse.commons.utils.string;

import com.seomse.commons.data.StartEnd;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 문자열 하이라이트
 * @author macle
 */
public class StringHighlight {

    /***
     * Highlight 문자열 생성
     * @param text String
     * @param pre String
     * @param post String
     * @param startEnds StartEnd []
     * @return String
     */
    public static String make(String text, String pre, String post, StartEnd [] startEnds){

        Arrays.sort(startEnds, Comparator.comparingInt(StartEnd::getStart));
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
