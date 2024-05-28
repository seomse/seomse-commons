package com.seomse.commons.exception;
/**
 * @author macle
 */
public class UndefinedException extends RuntimeException{

    public UndefinedException(){
        super();
    }

    public UndefinedException(String message){
        super(message);
    }
}
