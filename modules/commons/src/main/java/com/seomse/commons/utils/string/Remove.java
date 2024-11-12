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

import com.seomse.commons.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * 문자열 제거
 * @author macle
 */
public class Remove {


    public static String betweenFirst(String text, String beginStr, String endStr){
        return betweenFirst(text, false, beginStr, endStr);
    }

    public static String betweenFirst( String text,boolean isRemoveSymbol, String beginStr, String endStr) {

        int beginIndex = text.indexOf(beginStr);
        if(beginIndex == -1){
            return text;
        }

        int searchEndStart = beginIndex + beginStr.length();

        if(!isRemoveSymbol){
            beginIndex = searchEndStart;
        }

        int endIndex = text.indexOf(endStr,searchEndStart );
        if(endIndex == -1){
            return text;
        }

        if(isRemoveSymbol){
            endIndex = endIndex + endStr.length();
        }


        return text.substring(0, beginIndex) +
                text.substring(endIndex);
    }

    /**
     * tab enter 제거
     * @param str String
     * @return String
     */
    public static String tabEnter(String str){
        str = str.replace("\n", "");
        str = str.replace("\t", "");
        return str;
    }


    public static final String [] HTML_ENTER_ENTRY = getHtmlEnterEntry();

    public static String [] getHtmlEnterEntry(){
        String configValue = Config.getConfig("html.tag.enter.entry.array");
        if(configValue == null){
            return new String[0];
        }

        String [] values =  configValue.split(",");

        List<String> list = new ArrayList<>();
        for(String v : values){
            if(v.equals("")){
                continue;
            }
            list.add(v);
        }
        return list.toArray(new String[0]);
    }

    /**
     * HTML테그를 제거한다
     * @param str String 문자열
     * @return String
     */
    @SuppressWarnings({"RegExpSingleCharAlternation", "RegExpRedundantEscape"})
    public static String htmlTag(String str){

        str = str.replace("<p>","\n");
        str = str.replace(" ","");

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
        str =str.replace(" ", " ");
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
        str =str.replace("\u200B", "\n");
        str =str.replace("\uFEFF", "\n");

        for (String enterEntry : HTML_ENTER_ENTRY) {
            str = str.replace(enterEntry, "\n");
        }

        str =str.replaceAll(entry, ""); //기타엔트리제거

        for(;;){
            int spaceEnterSearch = str.indexOf(" \n");
            if(spaceEnterSearch < 0){
                break;
            }
            str = str.replace(" \n","\n");
        }

        for(;;){
            int spaceEnterSearch = str.indexOf("\n ");
            if(spaceEnterSearch < 0){
                break;
            }
            str = str.replace("\n ","\n");
        }

        str = Change.enterContinue(str);

        //&amp = &, &#39 = '.   //&#숫자 = 아스키 코드넘버
//		http://www.w3schools.com/html/html_entities.asp   엔트리표
        str =str.trim();
        return str;
    }

    public static String commentImage(String str){
        String [] values = str.split("\n");
        StringBuilder sb = new StringBuilder();
        for(String value : values){
            if(value.startsWith("//")){
                if(value.toLowerCase().endsWith(".jpg") || value.toLowerCase().endsWith(".jpeg")|| value.toLowerCase().endsWith(".png") ){
                    continue;
                }
            }
            sb.append("\n").append(value);
        }

        if(sb.length() == 0){
            return "";
        }

        return sb.substring(1);
    }

    public static void main(String[] args) {
        System.out.println(HTML_ENTER_ENTRY.length);
    }
}
