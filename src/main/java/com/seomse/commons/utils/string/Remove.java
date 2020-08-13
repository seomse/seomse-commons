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
 * 문자열 제거
 * @author macle
 */
public class Remove {


    /**
     * tab enter 제거
     * @param str string
     * @return string
     */
    public static String tabEnter(String str){
        str = str.replace("\n", "");
        str = str.replace("\t", "");
        return str;
    }

    /**
     * HTML테그를 제거한다
     * @param str 문자열
     * @return
     */
    public static String htmlTag(String str){
        str = Change.enterContinue(str);
        str = Change.spaceContinueTab(str);
//		"<(.|\n)*?>";
//		String tag = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";
//		String tag = "<(|\\n)*?>";  //<> 전부 다지움

        String tag = "<(/)?([\\?:a-zA-Z0-9\\-\\s-]*)(\\s[\\?:_a-zA-Z0-9\\s-]*=[^>]*)?(\\s)*(/)?>";
        String entry = "&[a-z0-9#]+;";
        String help = "<!--.*?-->|-->.*?";

        str =str.replaceAll(help, "");
//		str =str.replaceAll(tag, "");

        str =str.replace("<br>", "\n");

        str = str.replaceAll("<(/|[\\s]*/|/[\\s]*)?(br|BR)([\\s]*/|[\\s]*/[\\s]*)?>", "\n");
        str = str.replaceAll("<(p|P)[^>]*>", "\n\n");
        str = str.replaceAll("<\\/b>", "");
        str = str.replaceAll("<\\/B>", "");
        str = str.replaceAll(tag, "");
        //주석제거

        str =str.replace("&nbsp;", " ");
        str =str.replace("&#160;", " ");
        str =str.replace("&lt;", "<");
        str =str.replace("&#60;", "<");
        str =str.replace("&gt;", ">");
        str =str.replace("&#62;", ">");
        str =str.replace("&amp;", "&");
        str =str.replace("&#38;", "&");
        str =str.replace("&#39;", "'");
        str =str.replace("&#xD;", "'");

        str =str.replaceAll(entry, ""); //기타엔트리제거
        //&amp = &, &#39 = '.   //&#숫자 = 아스키 코드넘버
//		http://www.w3schools.com/html/html_entities.asp   엔트리표
        str =str.trim();
        return str;
    }
}
