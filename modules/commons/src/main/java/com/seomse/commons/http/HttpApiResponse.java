package com.seomse.commons.http;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author macle
 */
@Data
public class HttpApiResponse {

    protected int responseCode;
    protected String message;

    protected Map<String, List<String>> headerFields;

    public List<String> getCookieList(){
        return getHeader("Set-Cookie");
    }

    public List<String> getHeader(String key){
        List<String> cookieList = headerFields.get(key);
        if(cookieList == null){
            return Collections.emptyList();
        }

        return cookieList;
    }

}
