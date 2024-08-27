package com.seomse.commons.exception;

/**
 * @author macle
 */
public class ConfigException extends RuntimeException{

    /**
     * 생성자
     */
    public ConfigException(){
        super();
    }

    /**
     * 생성자
     * @param e 예외
     */
    public ConfigException(Exception e){
        super(e);
    }

    /**
     * 생성자
     * @param message exception meesage
     */
    public ConfigException(String message){
        super(message);
    }
}
