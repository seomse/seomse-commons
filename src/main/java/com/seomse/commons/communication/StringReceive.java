


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
	private char [] charBuffer;
	
	
	
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
