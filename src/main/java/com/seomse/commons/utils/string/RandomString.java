

package com.seomse.commons.utils.string;

/**
 * <pre>
 *  파 일 명 : RandomString.java
 *  설    명 : 랜덤한 문자열 생성
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.12
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author  Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */

public class RandomString {

	/**
	 * 8~13자리의 랜덤한 문자열을 생성한다 
	 * @return 랜덤 문자열 얻기
	 */
	public String create(){
		int  length =(int)((Math.random()*5)+8);
		return create(true, true, true, true, length);

	}
	
	/**
	 * 랜덤한 문자열을 생성한다
	 * @param lower 소문자 사용여부
	 * @param upper 대문자 사용여부
	 * @param number 숫자 사용여부
	 * @param special 특수문자 사용여부
	 * @param length 생성할 문자열의 길이
	 * @return 랜덤으로 생성한 패스워드
	 */
	public String create(boolean lower, boolean upper, boolean number, boolean special, int length){	
		if(length < 0)
			return "";
	
		if(!lower && !upper && !number && !special){
			lower=true; upper=true; number=true; special=true;
		}
		
		char [] passwordArray = new char[length];
		
		int optionCount = 0 ;
		if(lower){
			optionCount++;		
		}
		if(upper){
			optionCount++;		
		}
		if(number){
			optionCount++;		
		}
		if(special){
			optionCount++;		
		}
		for(int i=0 ; i<length ; i++){
			int chkCount = -1;
			int option = (int)(Math.random()*optionCount);			
			if(lower){
				chkCount++;
				if(option == chkCount){
					passwordArray[i] = lower();
					continue;
				}
			}
			if(upper){
				chkCount++;
				if(option == chkCount){
					passwordArray[i] = upper();
					continue;
				}
			}		
			if(number){
				chkCount++;
				if(option == chkCount){
					passwordArray[i] = number();
					
					continue;
				}
			}				
			if(special){
				chkCount++;
				if(option == chkCount){
					passwordArray[i] = special();
				}
			}		
		}
		return new String(passwordArray);
	}
	
	private char lower(){
		return (char)((int)((Math.random()*26)+97));
	}
	
	private char upper(){	
		return (char)((int)((Math.random()*26)+65));
	}
	
	private char number(){
		return (char)((int)((Math.random()*10)+48));
	}

	private  char [] specials = {'!','@','#','$','%','^','*','(',')','-','=','_','+','`','~','[',']','{','}','|',',','?'};
	
	/**
	 * 특수문자 배열설정
	 * @param specials 특수문자 배열 얻기
	 */
	public void setSpecials(char[] specials) {
		this.specials = specials;
	}

	//	private static char [] specials = {'!','@','#','$','%','^','&','*','(',')','-','=','_','+','`','~','[',']','{','}','|','"','<','>',',','?','/'};
	private char special(){
		return specials[((int)(Math.random()*specials.length))];
	}

//	public static void main(String args[]){
//
//		System.out.println(new RandomString().create(true,true,false,false,12) );
//	}
}