
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

package com.seomse.commons.communication;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class StringReceive {
	private Socket socket = null;
	private InputStreamReader reader = null;
	private char [] cbuf;
	
	
	
	/**
	 * 생성자
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public StringReceive(Socket socket, int bufSize) throws UnsupportedEncodingException, IOException{
		this.socket =socket;
		reader  = new InputStreamReader(socket.getInputStream(), CommunicationDefault.CHAR_SET);
		cbuf = new char[bufSize];
	}
	
	/**
	 * 메시지얻기
	 * @return
	 * @throws IOException
	 */
	public String receive() throws IOException{
	
		int i = reader.read(cbuf);
		if( i == -1){
			return null;
		}
		
		
		return new String(cbuf,0,i);
		
//		return null;
	}
	
	/**
	 * 연결 해제
	 */
	public void disConnect(){
		
		try{
			if(reader != null){
				reader.close();	
			}
		}catch(Exception e){}
		reader = null;
		try{
			if(socket != null ){
				socket.close();		
			
			}
		}catch(Exception e){}
		socket = null;
	}
	
}
