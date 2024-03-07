
package com.seomse.system.ftp;

/**
 * ftp 파일전송 종료 이후 작업 call back
 * @author mimimjin
 */
@SuppressWarnings("unused")
public interface FileReceiveCallback {
   /**
    *
    * @param message String
    */
   void callback(String message);
}
