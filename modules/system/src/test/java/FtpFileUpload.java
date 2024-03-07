

import com.seomse.system.ftp.client.FtpClient;

/**
 * ftp 서버에 파일 올리기
 * @author mimimjin
 */
public class FtpFileUpload {
	public static void main(String [] args) {

		FtpClient.upload("127.0.0.1" //host address
				, 10001 //port
				, "C:\\Users\\moara\\Downloads\\FileZilla_3.32.0_win64-setup.exe" // send file path
				, "/home/moara/FileZilla_3.32.0_win64-setup.exe" //receive save file path
		);
	}
}
