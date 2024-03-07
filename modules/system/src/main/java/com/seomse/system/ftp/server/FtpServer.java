
package com.seomse.system.ftp.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ftp 서버에 파일 올리기
 * @author mimimjin
 */
@Slf4j
@SuppressWarnings("unused")
public class FtpServer extends Thread{
	


	
	private final int port;
	
	private InetAddress inetAddress = null;
	
	private ServerSocket server = null;
	
	private int bufferArrSize = 1024;
	
	/* 서버소켓 실행 여부 */
	private boolean isRun = true;
	
	/**
	 * 생성자
	 * @param port 접속 통신 포트설정
	 */
	public FtpServer(int port){
		this.port = port;
	}
	
	/**
	 * byteArraySize 설정
	 * @param bufferArrSize int
	 */
	public void setBufferArrSize(int bufferArrSize) {
		this.bufferArrSize = bufferArrSize;
	}

	/**
	 * inetAddress 설정
	 * @param inetAddress InetAddress
	 */
	public void setInetAddress(InetAddress inetAddress) {
		this.inetAddress = inetAddress;
	}
	

	/**
	 * 서버 소켓 생성
	 * @return boolean
	 */
	public boolean newServerSocket(){
		try{
			if(server != null){
				return false;
			}
			if(inetAddress == null){
				server = new ServerSocket(port);
			}else{
				server = new ServerSocket(port, 50, inetAddress);
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public void run(){
		try{
			if(server == null){
				if(inetAddress == null){
					server = new ServerSocket(port);
				}else{
					server = new ServerSocket(port, 50, inetAddress);
				}
			}
			
			log.debug("ftp server start port: " + port);
			
			while(isRun){
				
				Socket connection = server.accept();
				FtpCommunication ftpCommunication = new FtpCommunication(connection, bufferArrSize);
				ftpCommunication.start();
			}
		}catch(IOException e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
		}
	}
	
	/**
	 * FTP 서버를 종료
	 */
	public void stopServer(){
		isRun = false;
		if(server == null) {
			return ;
		}
		

		try{ 
			server.close();
			server = null;
			
		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
		}
	}
	
}
