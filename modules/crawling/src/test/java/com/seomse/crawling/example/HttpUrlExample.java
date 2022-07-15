package com.seomse.crawling.example;

import com.seomse.crawling.core.http.HttpMessage;
import com.seomse.crawling.core.http.HttpUrl;
/**
 * @author macle
 */
public class HttpUrlExample {
    public static void main(String[] args) {
        HttpMessage message = HttpUrl.getMessage("https://www.naver.com/", HttpUrl.getChromeGetSimple("UTF-8"));
        System.out.println(message.getMessage());
    }
}
