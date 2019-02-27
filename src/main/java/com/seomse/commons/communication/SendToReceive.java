
/** 
 * <pre>
 *  파 일 명 : SendToReceive.java
 *  설    명 : 메시지 보내고 받기 
 *             대량 메시지 전송에는 적합하지 않음
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
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seomse.commons.handler.ExceptionHandler;
import com.seomse.commons.utils.ExceptionUtil;

public class SendToReceive {
	
	private static final Logger logger = LoggerFactory.getLogger(SendToReceive.class);
	
	private static final char START =(char)0;
	private static final char END =(char)1;
	
	
	
	private boolean isConnectErrorLog = true;
	
	private ExceptionHandler exceptionHandler;
	
	
	private Socket socket;
	private OutputStreamWriter writer;
	private InputStreamReader reader;
	private boolean readMessageFlag ;
	private Integer connectTimeOut = null;
	
	/**
	 * 생성자
	 * @param socket
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public SendToReceive(Socket socket) throws UnsupportedEncodingException, IOException{
	
		readMessageFlag = true;
		this.socket = socket;
			
		reader  = new InputStreamReader(socket.getInputStream(), CommunicationDefault.CHAR_SET);
		writer = new OutputStreamWriter(socket.getOutputStream(), CommunicationDefault.CHAR_SET);
	
	}
	
	
	

	/**
	 * 예외 핸들링설정
	 * @param exceptionHandler
	 */
	public void setExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}



	/**
	 * 연결에러로그 설정
	 * @param isConnectErrorLog
	 */
	public void setConnectErrorLog(boolean isConnectErrorLog){
		this.isConnectErrorLog = isConnectErrorLog;
	}
	
	
	/**
	 * 연결제한시간을 돌려준다.
	 * @return
	 */
	public Integer getConnectTimeOut() {
		return connectTimeOut;
	}


	/**
	 * 연결 제한시간을 설정한다.
	 * @param connectTimeOut
	 */
	public void setConnectTimeOut(Integer connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}



	public SendToReceive() {
		
	}
	
	
	/**
	 * 연결이끊어졌을경우 제연결한다.
	 */
	public boolean connect(String host, int port){
		if(socket == null || socket.isClosed()){


			readMessageFlag = true;
			try{
				
				if(connectTimeOut == null){
					socket = new Socket(host, port);
				}else{
					SocketAddress socketAddress = new InetSocketAddress(host, port);
					socket = new Socket();
					socket.setSoTimeout(connectTimeOut);
					socket.connect(socketAddress, connectTimeOut);
				}
				reader  = new InputStreamReader(socket.getInputStream(), "UTF-8");
				writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
				

				return true;
			}catch(Exception e){
				if(isConnectErrorLog){
					logger.error(host +", " + port + " : connect fail");
					ExceptionUtil.exception(e, logger, exceptionHandler);
				}
				disConnect();
				return false;
			}
			
		}
		
		
		readMessageFlag = true;
		return true;
		
	}
	/**
	 * 연결중인지 체크한다.
	 * @return
	 */
	public boolean isConnect(){
		if(socket == null || socket.isClosed()){
			return false;
		}
		return true;
	}
	
	/**
	 * 메시지를 돌려받는다
	 * @return
	 */
	public String receive(){
		int readData ;
		
		StringBuilder messageBuilder = new StringBuilder();
		while(readMessageFlag){
			try {
				
				try{
					readData = reader.read();
				}catch(java.net.SocketException se){
					return null;
				}
				
				
				if(readData == -1){
					return null;
				}
				
				char message = (char)readData;
				switch(message){
					case START: 
						messageBuilder.setLength(0);
						break;
					case END:
						return messageBuilder.toString();
						
					default:
						messageBuilder.append(message);
						break;
					
				}
				
				
			} catch (IOException e) {
				readMessageFlag = false;
				return null;
			}
		}
	
		return null;
	}
	
	
	/**
	 * 메시지전달
	 * null이나 빈값이 들어오면 전달하지 않는다.
	 * @param message 메시지를 전달한 
	 * @throws IOException 
	 */
	public boolean send(String message){
		if(message == null || message.equals(""))
			return false;
		
		//특수기호 제거
		message = message.replace(START, ' ');
		message = message.replace(END, ' ');
		
		try {
			writer.write(START+message+END);
			writer.flush();
		} catch (IOException e) {
			return false;
		}
		
		return true;
	
	}
	
	/**
	 * 연결을 해제한다. 
	 */
	public void disConnect(){
		readMessageFlag = false;
		
		try {if(reader!=null) reader.close(); } catch(Exception e){}
		reader=null;
		try {if(writer!=null) writer.close(); } catch(Exception e){}
		writer=null;
		try {if(socket!=null) socket.close(); } catch(Exception e){}	
		socket=null;
	}
	
	/**
	 * socket 얻기
	 * @return
	 */
	public Socket getSocket() {
		return socket;
	}
	
}
	