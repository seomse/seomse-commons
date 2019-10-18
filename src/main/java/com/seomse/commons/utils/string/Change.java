package com.seomse.commons.utils.string;
/**
 * <pre>
 *  파 일 명 : Change.java
 *  설    명 : 문자열 변환
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
public class Change {

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
}
