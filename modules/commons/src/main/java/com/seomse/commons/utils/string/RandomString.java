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

/**
 * 랜덤 문자열 생성
 * @author macle
 */
public class RandomString {

	/**
	 * 8~13자리의 랜덤한 문자열을 생성한다 
	 * @return String 랜덤 문자열 얻기
	 */
	public String create(){
		int  length =(int)((Math.random()*5)+8);
		return create(true, true, true, true, length);

	}
	
	/**
	 * 랜덤한 문자열을 생성한다
	 * @param lower boolean 소문자 사용여부
	 * @param upper boolean 대문자 사용여부
	 * @param number boolean 숫자 사용여부
	 * @param special boolean 특수문자 사용여부
	 * @param length int 생성할 문자열의 길이
	 * @return String 랜덤으로 생성한 패스워드
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

	//	private static char [] specials = {'!','@','#','$','%','^','&','*','(',')','-','=','_','+','`','~','[',']','{','}','|','"','<','>',',','?','/'};
	private  char [] specials = {'!','@','#','$','%','^','*','(',')','-','=','_','+','`','~','[',']','{','}','|',',','?'};
	
	/**
	 * 특수문자 배열설정
	 * @param specials char[] 특수문자 배열 얻기
	 */
	public void setSpecials(char[] specials) {
		this.specials = specials;
	}

	private char special(){
		return specials[((int)(Math.random()*specials.length))];
	}


}