package com.seomse.crypto;
/**
 * @author macle
 */
public class CryptoException extends RuntimeException{
    public CryptoException(Exception e){
        super(e);
    }


    public CryptoException(){
        super();
    }

    public CryptoException(String message){
        super(message);
    }
}
