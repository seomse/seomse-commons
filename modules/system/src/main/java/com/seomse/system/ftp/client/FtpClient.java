
package com.seomse.system.ftp.client;

import com.seomse.system.ftp.FtpDefault;
import lombok.extern.slf4j.Slf4j;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;

/**
 * ftp 서버에 파일 올리기
 * @author mimimjin
 */
@SuppressWarnings("UnusedReturnValue")
@Slf4j
public class FtpClient {
	


	public static final int DEFAULT_BUFFER_ARR_SIZE = 1024;
	
	
	/**
	 * 파일업로드
	 * @param host string host address
	 * @param port int port
	 * @param filePath string send file path
	 * @param savePath string receive file path
	 * @return boolean success fail flag
	 */
	public static boolean upload(String host, int port, String filePath, String savePath){
		return upload(host, port, filePath, savePath, FtpDefault.NULL_CLASS_NAME, FtpDefault.NULL_ARGUMENTS, DEFAULT_BUFFER_ARR_SIZE);
	}
		
	
	/**
	 * 파일업로드
	 * @param host string host address
	 * @param port int port
	 * @param filePath string send file path
	 * @param savePath string send file path
	 * @param fileReceiveCallbackClassName string file receive complete next job class full name
	 * @param fileReceiveCallbackMessage string string file receive complete  next job param message
	 * @param bufferArrSize int buffer size
	 * @return boolean success fail flag
	 */
	public static boolean upload( String host, int port, String filePath, String savePath, String fileReceiveCallbackClassName, String fileReceiveCallbackMessage, int bufferArrSize){
		Socket sock = null;
		DataOutputStream dos = null;
		FileInputStream fis = null;
		
		boolean result = true;
		
		try{
			
			sock = new Socket(host,port);
			
			/* 데이터 전송 위한 스트림 객체 생성 */
			dos = new DataOutputStream(sock.getOutputStream());
			
			byte[] b = new byte[bufferArrSize];
			int n;
			
			
			dos.writeUTF(savePath);
			dos.flush();
			
			
			
			if(fileReceiveCallbackClassName == null){
				fileReceiveCallbackClassName = FtpDefault.NULL_CLASS_NAME;
			}
			dos.writeUTF(fileReceiveCallbackClassName);
			dos.flush();
			
			/*
			 * 후처리 시 필요한 Arguments 전송 (후처리 시 필요한 Arguments가 없을 경우 "NONE"으로 전송)
			 */
			if(fileReceiveCallbackMessage == null){
				fileReceiveCallbackMessage = FtpDefault.NULL_ARGUMENTS;
			}
			dos.writeUTF(fileReceiveCallbackMessage);
			dos.flush();
			
			
			fis = new FileInputStream(filePath);
				
			while((n=fis.read(b))!=-1){
				dos.write(b,0,n);
			}
			
			fis.close();
			dos.close();
			sock.close();

			log.debug("file send complete : " + filePath);

		}catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
			result = false;
		}
		
		if(fis != null) try{fis.close();}catch(Exception ignored) {}
		if(dos != null) try{dos.close();}catch(Exception ignored) {}
		if(sock != null) try{sock.close(); }catch(Exception ignored) {}
		return result;
		
	}

}
