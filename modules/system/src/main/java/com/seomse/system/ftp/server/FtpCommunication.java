
package com.seomse.system.ftp.server;

import com.seomse.system.ftp.FileReceiveCallback;
import com.seomse.system.ftp.FtpDefault;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * ftp 서버 시작
 * @author mimimjin
 */
@Slf4j
public class FtpCommunication extends Thread{

	private final Socket socket;
	private final DataInputStream dis;
	
	private final int bufferArrSize;

	/**
	 * 생성자
	 * @param socket Socket
	 * @param bufferArrSize int
	 * @throws IOException IOException
	 */
	FtpCommunication(Socket socket, int bufferArrSize) throws IOException{
		this.socket = socket;
		this.bufferArrSize = bufferArrSize;
		dis = new DataInputStream(socket.getInputStream());
	}

	
	@Override
	public void run(){
		String filePath = null;
		FileReceiveCallback receiveCallback;
		String argumentStr = null;
		
		FileOutputStream fos = null;

		String callbackClassName = null;
		try{
			
			filePath = dis.readUTF();
			
			callbackClassName = dis.readUTF();

			
			argumentStr = dis.readUTF();
			
			File saveFile = new File(filePath);
			
			if(saveFile.getParentFile().mkdirs()){
				log.debug("new directory create: "+saveFile.getParent());
			}
			
			fos = new FileOutputStream(filePath);
			byte[] b = new byte[bufferArrSize];
			int n;
			long fileSize = 0;
			while((n=dis.read(b))!= -1){ //전송된 데이터 읽어와 b 배열에 저장
				//b배열의 데이터를 파일로 저장
				fos.write(b,0, n);
				fileSize += n; //전송된 파일크기 구하기
			}
			log.debug("received file size: "+fileSize);

			fos.flush();
			fos.getFD().sync();

		}catch(IOException e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

		}
		if(fos != null)try{fos.close(); }catch(Exception ignored) {}
		try{dis.close(); }catch(Exception ignored) {}
		try{socket.close(); }catch(Exception ignored) {}
		
		log.debug("file receive complete : " + filePath);

		/* 후 처리 클래스가 있는 경우 */
		if(callbackClassName!= null && !callbackClassName.equals(FtpDefault.NULL_CLASS_NAME)){
			try{
				receiveCallback = (FileReceiveCallback) Class.forName(callbackClassName).newInstance();
				receiveCallback.callback(argumentStr);
			}catch(Exception e){
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				log.error(sw.toString());
			}
		}
	}
}