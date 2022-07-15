package com.seomse.crawling.core.http;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * active change init
 * @author macle
 */
public class HttpMessage {

    String message;

    Map<String, List<String>> headerFields;

    public List<String> getCookieList(){
        List<String> cookieList = headerFields.get("Set-Cookie");
        if(cookieList == null){
            return Collections.emptyList();
        }

        return cookieList;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }
}
