package com.seomse.commons.exception;

import java.net.SocketException;
/**
 * SocketException
 * @author macle
 */
public class SocketRuntimeException extends IORuntimeException{

    /**
     * 생성자
     */
    public SocketRuntimeException(){
        super();
    }

    /**
     * 생성자
     * @param e 예외
     */
    public SocketRuntimeException(SocketException e){
        super(e);
    }

    /**
     * 생성자
     * @param message exception meesage
     */
    public SocketRuntimeException(String message){
        super(message);
    }
}
