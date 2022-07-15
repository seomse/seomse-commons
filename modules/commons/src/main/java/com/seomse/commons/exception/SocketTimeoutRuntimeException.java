package com.seomse.commons.exception;

import java.net.SocketTimeoutException;
/**
 *  SocketTimeoutException
 * @author macle
 */
public class SocketTimeoutRuntimeException extends IORuntimeException{

    /**
     * 생성자
     */
    public SocketTimeoutRuntimeException(){
        super();
    }

    /**
     * 생성자
     * @param e 예외
     */
    public SocketTimeoutRuntimeException(SocketTimeoutException e){
        super(e);
    }

    /**
     * 생성자
     * @param message exception meesage
     */
    public SocketTimeoutRuntimeException(String message){
        super(message);
    }
}
