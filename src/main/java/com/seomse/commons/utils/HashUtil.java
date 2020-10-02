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

package com.seomse.commons.utils;

import java.security.NoSuchAlgorithmException;

/**
 * Hash 관련 유틸
 * @author macle
 */
public class HashUtil {
	

	/**
	 * 특정해쉬알고리즘의 문자열 값 얻기
	 * @param hash String "MD5","SHA1","SHA-256","SHA-384","SHA-512"
	 * @param value String hash target value
	 * @return String hash
	 * @throws NoSuchAlgorithmException NoSuchAlgorithmException
	 */
	public static String getHash(String hash, String value) throws NoSuchAlgorithmException{
		return  getHash(java.security.MessageDigest.getInstance(hash), value);
	}
	
	/**
	 * 특정해쉬알고리즘의 문자열 값 얻기
	 * @param messageDigest MessageDigest 알고리즘
	 * @param value String hash target
	 * @return String hash
	 */
	public static String getHash(java.security.MessageDigest messageDigest, String value){
		messageDigest.update(value.getBytes());
		byte[] byteData = messageDigest.digest();
		StringBuilder sb = new StringBuilder();
		//noinspection ForLoopReplaceableByForEach
		for(int i = 0 ; i < byteData.length ; i++){
			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}
}
