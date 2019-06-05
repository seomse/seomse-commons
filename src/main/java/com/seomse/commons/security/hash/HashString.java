

package com.seomse.commons.security.hash;

import java.security.NoSuchAlgorithmException;
/**
 * <pre>
 *  파 일 명 : HashString.java
 *  설    명 : Hash값으로 고유의 스트링값생성
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.07
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */
public class HashString {
	
	/***
	 * hash값을 문자열로 변환한 결과얻기 ( 관련패키지 고유결과)
	 * @param hash "MD5","SHA1","SHA-256","SHA-384","SHA-512"
	 * @param value 문자열
	 * @return  change hash
	 */
	public static String getResult(String hash, String value) throws NoSuchAlgorithmException{
		return  getResult(java.security.MessageDigest.getInstance(hash), value);
	}
	
	
	
	/**
	 * hash값을 문자열로 변환한 결과얻기  ( 관련패키지 고유결과)
	 * @param messageDigest 해쉬알고리즘 종류 
	 * @param value 문자열
	 * @return change hash
	 */
	public static String getResult(java.security.MessageDigest messageDigest, String value){
        String eip;
        byte[] bip;
        bip = messageDigest.digest(value.getBytes());
        int length =  bip.length;
        char addc;
        if (value.length() > 0){
        	addc = value.charAt(0);
        }else{
        	addc = ' ';
        }
        StringBuilder temp = new StringBuilder();
		//noinspection ForLoopReplaceableByForEach
		for (int i = 0; i < length; i++) {
            eip = "" + Integer.toHexString((int) bip[i] & 0x000000ff);
            if (eip.length() < 2)
                eip = "0" + eip;
            temp.append(addc).append(eip);
        }
        temp.append(value);
        StringBuilder resultBuilder = new StringBuilder();
        bip=messageDigest.digest(temp.toString().getBytes());
        for (int i = 0; i <length; i++) {
        	int resultchar = ((int) bip[i] & 0x000000ff);
           	resultchar=resultchar%127+33;
           	if(resultchar>126){
        	   if(resultchar <= 135){
        		   resultchar= resultchar - 19;
        	   }else if(resultchar <= 148){
        		   resultchar= resultchar - 36;
        	   }else{
        		   resultchar= resultchar- 86;
        	   }
           }
           if(resultchar == 92){
        	   resultchar -= i ;
           }
           	
           resultBuilder.append((char)resultchar);

        }
        return resultBuilder.toString();
	}
}
