
import com.seomse.system.ftp.server.FtpServer;

/**
 * ftp 서버 시작
 * @author mimimjin
 */
public class ServerStart {
	public static void main(String [] args) {
		FtpServer ftpServer = new FtpServer(10001);
		ftpServer.start();
	}
}
