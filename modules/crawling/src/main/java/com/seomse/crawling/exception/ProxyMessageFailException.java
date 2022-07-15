package com.seomse.crawling.exception;
/**
 * 프록시 통신 실패 처리
 * @author macle
 */
public class ProxyMessageFailException extends RuntimeException{
    public ProxyMessageFailException(){
        super();
    }
    
    public ProxyMessageFailException(String msg){
        super(msg);
    }

}
