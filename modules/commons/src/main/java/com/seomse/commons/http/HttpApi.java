package com.seomse.commons.http;

import com.seomse.commons.exception.IORuntimeException;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/**
 * @author macle
 */
@Setter
public class HttpApi {


    private String defaultAddress ="";
    private String defaultMethod = "GET";

    private Integer connectTimeOut= null;

    private Integer readTimeOut= null;

    private Charset defaultCharSet= StandardCharsets.UTF_8;


    private Map<String, String> defaultRequestProperty = null;

    public HttpApi(){

    }

    public void setRequestProperty(String key, String value){
        if(defaultRequestProperty == null){
            defaultRequestProperty = new HashMap<>();
        }
        defaultRequestProperty.put(key,value);
    }


    public String getMessage(String urlAddr){
        return getResponse(urlAddr, defaultMethod, null, null, defaultCharSet).getMessage();
    }


    public HttpApiResponse getResponse(String urlAddr){
        return getResponse(urlAddr, defaultMethod, null, null, defaultCharSet);
    }

    public String getMessage(String urlAddr,  String outStreamParam){
        return getResponse(urlAddr, defaultMethod, null, outStreamParam, defaultCharSet).getMessage();
    }

    public HttpApiResponse getResponse(String urlAddr, String outStreamParam){
        return getResponse(urlAddr, defaultMethod, null, outStreamParam, defaultCharSet);
    }

    public String getMessage(String urlAddr, Map<String, String> requestProperty ,String outStreamParam){
        return getResponse(urlAddr, defaultMethod, requestProperty, outStreamParam, defaultCharSet).getMessage();
    }

    public HttpApiResponse getResponse(String urlAddr, Map<String, String> requestProperty , String outStreamParam){
        return getResponse(urlAddr, defaultMethod, requestProperty, outStreamParam, defaultCharSet);
    }

    public String getMessage(String urlAddr, Map<String, String> requestProperty ){
        return getResponse(urlAddr, defaultMethod, requestProperty, null, defaultCharSet).getMessage();
    }


    public HttpApiResponse getResponse(String urlAddr, Map<String, String> requestProperty){
        return getResponse(urlAddr, defaultMethod, requestProperty, null, defaultCharSet);
    }


    public HttpApiResponse getResponse(String urlAddr, String method, Map<String, String> requestProperty, String outStreamParam, Charset charset){

        HttpApiResponse response = new HttpApiResponse();
        try {
            URL url = new URL(defaultAddress+urlAddr);
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            if(method == null){
                conn.setRequestMethod(defaultMethod);
            }else{
                conn.setRequestMethod(method);
            }

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);


            if(readTimeOut != null) {
                conn.setReadTimeout(readTimeOut);
            }

            if(connectTimeOut != null) {
                conn.setConnectTimeout(connectTimeOut);
            }

            if(defaultRequestProperty != null){
                Set<String> keys = defaultRequestProperty.keySet();

                for(String key: keys){
                    conn.setRequestProperty(key, defaultRequestProperty.get(key));
                }
            }

            if(requestProperty != null){
                Set<String> keys = requestProperty.keySet();

                for(String key: keys){
                    conn.setRequestProperty(key, requestProperty.get(key));
                }
            }

            if (outStreamParam != null) {
                byte[] contents = outStreamParam.getBytes(charset);
                OutputStream outSteam = conn.getOutputStream();
                outSteam.write(contents);
                outSteam.flush();
                outSteam.close();
            }

            HttpApiResponse httpResponse = new HttpApiResponse();
            httpResponse.setResponseCode(conn.getResponseCode());
            httpResponse.setHeaderFields(conn.getHeaderFields());
            httpResponse.setMessage(getMessage(conn, charset));

            try{
                conn.disconnect();
            }catch (Exception ignore){}

            return httpResponse;
        }catch (IOException e){
            throw new IORuntimeException(e);
        }

    }


    public static String getMessage(HttpURLConnection conn, Charset charset){
        StringBuilder message = new StringBuilder();
        BufferedReader br = null;
        try {
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if(Objects.equals(conn.getContentEncoding(), "gzip")) {
                    try {
                        br = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream()), charset));
                    } catch (IOException e) {
                        br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
                    }
                }
                else {
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
                }
                for (;;) {
                    String line = br.readLine();
                    if (line == null) break;
                    message.append(line).append('\n');
                }

            } else {
                try {
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
                    for (;;) {
                        String line = br.readLine();
                        if (line == null) break;
                        message.append(line).append('\n');
                    }
                }catch (Exception ignore){}

                if(message.length() == 0){
                    br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), charset));
                    for (;;) {
                        String line = br.readLine();
                        if (line == null) break;
                        message.append(line).append('\n');
                    }
                }

            }
        }catch (IOException e){
            throw new IORuntimeException(e);
        } finally{
            try{
                if(br != null) {
                    br.close();
                }
            }catch(Exception ignore){}
        }

        if(message.length()>0){
            //마지막 엔터제거
            message.setLength(message.length()-1);
        }
        return message.toString();
    }

}
