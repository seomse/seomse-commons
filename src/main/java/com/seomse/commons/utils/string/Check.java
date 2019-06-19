


package com.seomse.commons.utils.string;

import java.lang.Character.UnicodeBlock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <pre>
 *  파 일 명 : Check.java
 *  설    명 : 문자열 유효성 체크 관련 클래스
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
public class Check {

	/**
	 * 이메일 유효성 체크 
	 * @param str 체크할 문자열
	 * @return
	 */
	public static boolean isEmailPattern(String str){
		Pattern pattern=Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+$");
		Matcher match=pattern.matcher(str);
		return match.find();
	}

	/**
	 * 문자열이 숫자로만 이루어져 있는지 체크
	 * @param str 체크할 문자열
	 * @return
	 */
	public static boolean isNumber(String str){
		if(str == null)
			return false;
		
		if(str.length()==0){
			return false;
		}
		
		int length = str.length();
		for (int i=0; i < length ; i++){
			char chk = str.charAt(i);
			if(chk>57 || chk<48)
				return false;		
		}
		return true;
	}
	
	/**
	 * char가 숫자인지 체크
	 * @param ch 체크할 케릭터
	 * @return
	 */
	public static boolean isNumber(char ch){
		return ch <= 57 && ch >= 48;
	}
	
	/**
	 * 숫자형 형태인지 체크 (소수점 포함)
	 * @param str 체크할 문자열
	 * @return 
	 */
	public static boolean isNumberType(String str){
		if(str == null || str.length()<1){
			return false;
		}
		
		String chkStr = str.replace(".", "");
		if(str.length() - chkStr.length() > 1 || chkStr.length() ==0)
			return false;

		int commaCount = str.length() - str.replace(",", "").length();
		if(str.length() < commaCount*3+1)
			return false;
		str = str.replace(",", "");
		
		if(str.length()<1){
			return false;
		}
		
		char [] array = str.toCharArray();
		if(array[0] == '.')
			return false;
		
		if(array[array.length-1] == '.')
			return false;

		//noinspection ForLoopReplaceableByForEach
		for (int i=0; i < array.length ; i++){
			char chk = array[i];
			if((chk>57 || chk<48 ) && chk !='.')
				return false;		
		}
		return true;
	}

	/**
	 * 영어로 이루어져 있는지 체크한다.
	 * @param str 체크할 문자열
	 * @return
	 */
	public static boolean isEng(String str){
		str = str.toLowerCase();
		int length = str.length();
		for (int i=0; i < length ; i++){
			char chk = str.charAt(i);
			if(chk>122 || chk<97)
				return false;		
		}
		return true;
	}
	
	/**
	 * 케릭터가 영어로 이루어져 있는지 체크한다.
	 * @param ch
	 * @return
	 */
	public static boolean isEng(char ch){
		
		ch = Character.toLowerCase(ch);
		return ch <= 122 && ch >= 97;
	}
	
	
	
	/**
	 * 한글자모인지 체크
	 * @param ch
	 * @return
	 */
	public static boolean isHangulJamo(char ch) {
	
		UnicodeBlock block = UnicodeBlock.of(ch);

		return UnicodeBlock.HANGUL_JAMO == block
				|| UnicodeBlock.HANGUL_COMPATIBILITY_JAMO == block;

	}
	
	
	
	/**
	 * 한글인지 체크한다.
	 * @param ch
	 * @return
	 */
	public static boolean isHangul(char ch) {
	
		UnicodeBlock block = UnicodeBlock.of(ch);

		return UnicodeBlock.HANGUL_SYLLABLES == block
				|| UnicodeBlock.HANGUL_JAMO == block
				|| UnicodeBlock.HANGUL_COMPATIBILITY_JAMO == block;

	}

	/**
	 * 한글 완성형 문자인지 체크한다.
	 * @param ch
	 * @return
	 */
	public static boolean isHangulSyllable(char ch) {
	
		UnicodeBlock block = UnicodeBlock.of(ch);

		return UnicodeBlock.HANGUL_SYLLABLES == block;

	}
	
	/**
	 * 한글 완성형 문자인지 체크한다.
	 * @param str
	 * @return
	 */
	public static boolean isHangulSyllable(String str) {
	
		int length = str.length();
		for (int i=0; i < length ; i++){
			if(!isHangulSyllable(str.charAt(i))){
				return false;
			}
		}
	

	    return true;
	
	}
	
	/**
	 * 완성형한글문자와 숫자 스페이스로만 이루어진 글자인지 검사한다.
	 * @param str
	 * @return
	 */
	public static boolean isHangulAndNumber(String str) {
	
		int length = str.length();
		for (int i=0; i < length ; i++){
			
			char ch = str.charAt(i);
			if(!isHangulSyllable(ch)
					&& ch !=' '
					&& !isNumber(ch)){
				return false;
			}
		}
	

	    return true;
	
	}
	
	
	/**
	 * 음절형인지 체크한다(완성형 한글자이거나, 영문자이거나, 숫자일때 true)
	 * @param ch
	 * @return
	 */
	public static boolean isSyllableType(char ch){
		return isHangulSyllable(ch)
				|| isNumber(ch)
				|| isEng(ch);


	}
	
	
	/**
	 * null 을포함해서 두개의 문자열이 같은지 비교
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isEquals(String a, String b){
		if(a == null && b == null){
			return true;
		}
		
		if(a != null && b == null){
			return false;
		}
		
		if(a == null){
			return false;
		}
		
		return a.equals(b);		
		
	}
	
	
//	private static char [] specials = {'!','@','#','$','%','^','&','*','(',')','-','=','_','+','`','~','[',']','{','}','|','"','<','>',',','?','/','¨'
//		,'！','＇'};
	/**
	 * 특수문자여부
	 * @param ch
	 * @return
	 */
	public static boolean isSpecialCharacter(char ch){
		
		String check = Character.toString(ch);
		
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";

		check =check.replaceAll(match, "");

		return check.length() == 0;


	}
	
	/**
	 * 특수문자여부
	 * @param 
	 * @return
	 */
	public static boolean isSpecialCharacter(String str){
		
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";

		str =str.replaceAll(match, "");

		return str.length() == 0;


	}
	
	
	/**
	 * 문자열에 숫자가 없는지체크
	 * @param str 체크할 문자열
	 * @return
	 */
	public static boolean isNotNumber(String str){
		if(str == null)
			return true;
		
		if(str.length()==0){
			return true;
		}
		
		int length = str.length();
		for (int i=0; i < length ; i++){
			char chk = str.charAt(i);
			if(  chk>=48 && chk<=57)
				return false;		
		}
		return true;
	}
	
}