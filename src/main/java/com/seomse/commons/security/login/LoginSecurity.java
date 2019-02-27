/** 
 * <pre>
 *  파 일 명 : LoginSecurity.java
 *  설    명 : 로그인 보안 
 *            seed128은 16자리키로 생성해야 바이트가맞음 md5(해쉬알고리즘)를 활용하여 16자리의 스트링을 생성하는 알고리즘활용   
 *  작 성 자 : macle
 *  작 성 일 : 2017.07
 *  버    전 : 1.0
 *  수정이력 : 
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */

package com.seomse.commons.security.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seomse.commons.security.hash.HashString;
import com.seomse.commons.security.seed.Seed128Cipher;
import com.seomse.commons.utils.ExceptionUtil;

public class LoginSecurity {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginSecurity.class);
	
	/**
	 * 로그인정보 암호화
	 * @param id 아이디
	 * @param password 패스워드
	 * @return
	 */
	public static LoginInfo encryption(String id, String password){
		
		try{
			//seed128은 16자리키로 생성해야 바이트가맞음 md5를 활용하여 16자리의 스트링을 생성하는 알고리즘활용
			//아이디를 이용하여 패스워드 키생성 
			String encPasswordKey = HashString.getResult("MD5",id);
			//패스워드암호화
			String encPassword = Seed128Cipher.encrypt(password, encPasswordKey.getBytes(), "UTF-8");
			//암호화된 패스워드를 이용하여 아이디 키생성
			String idKey = HashString.getResult("MD5",encPassword);
			//아이디 암호화
			String encId = Seed128Cipher.encrypt(id, idKey.getBytes(), "UTF-8");
		
			LoginInfo loginInfo = new LoginInfo(encId, encPassword);
			return loginInfo;
		}catch(Exception e){
			logger.error(ExceptionUtil.getStackTrace(e));
			return null;
		}
	}
	
	/***
	 * 로그인정보 복호화
	 * @param encryptionId 암호화된 아이디
	 * @param encryptionPassword 암호화된 패스워드
	 * @return
	 */
	public static LoginInfo decryption(String encryptionId, String encryptionPassword){
	
		try{
			//암호화된 패스워드를 이용해서 아이디 복호화키생성
			String decIdKey = HashString.getResult("MD5",encryptionPassword);
			//아이디복호화
			String id = Seed128Cipher.decrypt(encryptionId, decIdKey.getBytes(), "UTF-8");
			//패스워드 복호화 키생성
			String decPasswordKey =  HashString.getResult("MD5", id);
			//패스워드 복호화
			String password = Seed128Cipher.decrypt(encryptionPassword,  decPasswordKey.getBytes(), "UTF-8");
			LoginInfo loginInfo = new LoginInfo(id, password);
			return loginInfo;
		}catch(Exception e){
			logger.error(ExceptionUtil.getStackTrace(e));
			return null;
		}
		
		
	}
	
//	public static void main(String args[]){
//		LoginInfo loginInfo = encryption("seomse_stock", "SlWZyLhGfLoF");
//		System.out.println(loginInfo.getId() +" " + loginInfo.getPassword());
//	
//		loginInfo = decryption(loginInfo.getId(), loginInfo.getPassword());
//		System.out.println(loginInfo.getId() +" " + loginInfo.getPassword());
//	}
	
}
