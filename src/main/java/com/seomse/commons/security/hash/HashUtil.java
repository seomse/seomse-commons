/** 
 * <pre>
 *  파 일 명 : HashUtil.java
 *  설    명 : Hash관련 유틸성클래스 
 *         
 *  작 성 자 : macle
 *  작 성 일 : 2017.07
 *  버    전 : 1.0
 *  수정이력 : 
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */


package com.seomse.commons.security.hash;

import java.security.NoSuchAlgorithmException;

public class HashUtil {
	
	
	/***
	 * 특정해쉬알고리즘의 값 얻기
	 * @param hash "MD5","SHA1","SHA-256","SHA-384","SHA-512"
	 * @param value
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String getHash(String hash, String value) throws NoSuchAlgorithmException{
		return  getHash(java.security.MessageDigest.getInstance(hash), value);
	}
	
	/**
	 * 특정해쉬알고리즘의 값 얻기
	 * @param hash
	 * @param value
	 * @return
	 */
	public static String getHash(java.security.MessageDigest messageDigest, String value){
		

		messageDigest.update(value.getBytes()); 
		byte byteData[] = messageDigest.digest();
		StringBuilder sb = new StringBuilder(); 
		for(int i = 0 ; i < byteData.length ; i++){
			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
		}
			
		return sb.toString();
			
	
	}
}
