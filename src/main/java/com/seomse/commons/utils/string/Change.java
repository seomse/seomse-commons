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

/**
 * 문자열 변환
 * @author macle
 */
public class Change {
    /**
     * 이중공백을 하나의 스페이스로 치환한다.
     * @param str 문자열
     * @return String
     */
    public static String spaceContinue(String str){
        String match2 = "\\s{2,}";
        str = str.replaceAll(match2, " ");
        return str;
    }

    /**
     * 두개이상의 엔터가 하나의 엔터로 치환된다.
     * @param str 문자열
     * @return String
     */
    public static String enterContinue(String str){
//		return str.replace("\\n{2,}", "\n");
//		return str.replaceAll("[\\n]+", "\n");

        return str.replaceAll("[\n|\r|\r\n]+", "\n");
    }

    /**
     * 2개이상의 Space,Tab가 하나의 Space로 치환된다.
     * @param str 문자열
     * @return String
     */
    public static String spaceContinueTab(String str){
        return str.replaceAll("\\p{Blank}{2,}", " ");
    }

    /**
     * 특수문자를 변경한다
     * @param str 변경할 문자열
     * @param changeValue 변경될 값
     * @return
     */
    public static String special(String str, String changeValue){
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        str = str.replaceAll(match, changeValue);
        return str;
    }

    /**
     * 숫자 사이에 있는 .(dot)을 스페이스로 변경한다
     * @param str
     * @return
     */
    public static String noNumberDot(String str){

        char [] charArray = str.toCharArray();
        if(str.length() < 2){
            return str;
        }

        if(charArray[0] == '.' ){
            charArray[0] = ' ';
        }

        for(int i=1 ; i<charArray.length -1; i++){
            if(charArray[i] =='.'){
                if(!Check.isNumber(charArray[i - 1]) || !Check.isNumber(charArray[i + 1])) {
                    charArray[i] =' ';
                }
            }
        }
        if(charArray[charArray.length -1] == '.' ){
            charArray[charArray.length -1] = ' ';
        }


        return new String(charArray).trim();
    }
}
