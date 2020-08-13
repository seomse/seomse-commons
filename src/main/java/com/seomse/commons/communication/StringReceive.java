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


package com.seomse.commons.communication;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
/**
 * <pre>
 *  파 일 명 : StringReceive.java
 *  설    명 : (문자열) 받기
 *
 *  작 성 자 : macle
 *  작 성 일 : 2018.04
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2018 by ㈜섬세한사람들. All right reserved.
 */
public class StringReceive {
	private Socket socket ;
	private InputStreamReader reader ;
	private final char [] charBuffer;
	
	
	
	/**
	 * 생성자
	 */
	public StringReceive(Socket socket, int bufSize) throws IOException{
		this.socket =socket;
		reader  = new InputStreamReader(socket.getInputStream(), CommunicationDefault.CHAR_SET);
		charBuffer = new char[bufSize];
	}
	
	/**
	 * 메시지얻기
	 */
	public String receive() throws IOException{
	
		int i = reader.read(charBuffer);
		if( i == -1){
			return null;
		}
		
		
		return new String(charBuffer,0,i);
	}
	
	/**
	 * 연결 해제
	 */
	public void disConnect(){

		//noinspection CatchMayIgnoreException
		try{
			if(reader != null ){
				reader.close();	
			}
		}catch(Exception e){}

		reader = null;
		//noinspection CatchMayIgnoreException
		try{
			if(socket != null && !socket.isClosed() ){
				socket.close();
			}
		}catch(Exception e){}
		socket = null;
	}
	
}
