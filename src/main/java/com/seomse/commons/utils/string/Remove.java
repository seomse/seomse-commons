package com.seomse.commons.utils.string;
/**
 * <pre>
 *  파 일 명 : Remove.java
 *  설    명 : 조건에 맞는 문자열 제거
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.09
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author  Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */
public class Remove {

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
