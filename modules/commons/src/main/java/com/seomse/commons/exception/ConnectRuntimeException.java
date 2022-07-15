package com.seomse.commons.exception;

import java.net.ConnectException;
/**
 * ConnectException
 * @author macle
 */
public class ConnectRuntimeException  extends SocketRuntimeException{

    /**
     * 생성자
     */
    public ConnectRuntimeException(){
        super();
    }

    /**
     * 생성자
     * @param e 예외
     */
    public ConnectRuntimeException(ConnectException e){
        super(e);
    }

    /**
     * 생성자
     * @param message exception meesage
     */
    public ConnectRuntimeException(String message){
        super(message);
    }
}
