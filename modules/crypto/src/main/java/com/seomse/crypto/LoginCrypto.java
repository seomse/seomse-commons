package com.seomse.crypto;


/**
 *
 * 로그인 정보 암복호화
 * 로그인 정보를 이용하여 키를 생성하고 암복호화 하기 떄문에 방법을 모르면 괜찮아도
 * 방법을 알면 알고리즘으로 복호화가 가능해서 위험할 수 있음
 * @author macle
 */
@SuppressWarnings("unused")
public class LoginCrypto {
	
	/**
	 * 로그인 정보 암호화
	 * @param id string id
	 * @param password string password
	 * @return string []  string[0] = id enc , string[1] = password enc
	 */
	public static String [] encryption(String id, String password){
		
		try{
			//아이디를 이용하여 패스워드 키생성 
			String encPasswordKey = HashConfusionString.get("MD5",id);

			//패스워드암호화
			String encPassword = StringCrypto.enc(encPasswordKey, password);

			//암호화된 패스워드를 이용하여 아이디 키생성
			String idKey = HashConfusionString.get("MD5",encPassword);
			//아이디 암호화
			String encId = StringCrypto.enc(idKey, id);
			
			return new String[] {encId, encPassword};
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/***
	 * 로그인 정보 복호화
	 * @param encryptionId 암호화된 아이디
	 * @param encryptionPassword 암호화된 패스워드
	 * @return  string []  string[0] = id  , string[1] = password
	 */
	public static  String [] decryption(String encryptionId, String encryptionPassword){
	
		try{
			//암호화된 패스워드를 이용해서 아이디 복호화키생성
			String decIdKey = HashConfusionString.get("MD5",encryptionPassword);

			//아이디복호화
			String id = StringCrypto.dec(decIdKey, encryptionId);

			//패스워드 복호화 키생성
			String decPasswordKey = HashConfusionString.get("MD5", id);
			//패스워드 복호화
			String password = StringCrypto.dec(decPasswordKey, encryptionPassword);

			return new String[] {id, password};
		}catch(Exception e){

			throw new RuntimeException(e);
		}
		
		
	}
	

	
}
