package com.seomse.commons.exception;

/**
 * token 관련 예외
 * @author macle
 */
public class TokenException  extends RuntimeException{

    /**
     * 생성자
     */
    public TokenException(){
        super();
    }

    /**
     * 생성자
     * @param e 예외
     */
    public TokenException(Exception e){
        super(e);
    }

    /**
     * 생성자
     * @param message exception meesage
     */
    public TokenException(String message){
        super(message);
    }
}
