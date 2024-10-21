package com.seomse.commons.http;

import com.seomse.commons.exception.IORuntimeException;
import com.seomse.commons.utils.FileUtil;
import lombok.Setter;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
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


    private Integer fileReadTimeOut= null;


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

    public File downloadFile(String urlAddress, String downloadPath){
        InputStream in = null;
        FileOutputStream fos = null ;
        HttpURLConnection conn = null ;

        File pathFile = new File(downloadPath);
        String dirPath = pathFile.getParentFile().getAbsolutePath();
        if(!FileUtil.isDirectory(dirPath)){
            //noinspection ResultOfMethodCallIgnored
            new File(dirPath).mkdirs();
        }

        if(pathFile.isFile()){
            //noinspection ResultOfMethodCallIgnored
            pathFile.delete();
        }

        try {
            File file = null;
            URL url = new URL(defaultAddress+urlAddress);
            conn = (HttpsURLConnection) url.openConnection();

            if(fileReadTimeOut != null){
                conn.setReadTimeout(fileReadTimeOut);
            }else{
                if(readTimeOut != null) {
                    conn.setReadTimeout(readTimeOut);
                }
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

            if (conn != null && conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                file = new File(downloadPath);
                //noinspection ResultOfMethodCallIgnored
                file.getParentFile().mkdirs();
                if(file.exists()){
                    //noinspection ResultOfMethodCallIgnored
                    file.delete();
                }
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
                in = conn.getInputStream();
                fos = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int len1 ;
                while ((len1 = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                }

                fos.getFD().sync();

                fos.close();
                in.close();


            }
            return file;
        }
        catch (IOException e) {
            throw new IORuntimeException(e);
        }finally{
            if(in != null){
                try{in.close();}catch(Exception ignore){}
            }
            if(fos != null){
                try{fos.close();}catch(Exception ignore){}
            }
            if(conn != null){
                try{  conn.disconnect();}catch(Exception ignore){}
            }
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
