package com.comse.telegram.config;

import com.seomse.commons.config.Config;
import com.seomse.commons.exception.IORuntimeException;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * 텔레그램 콘피그 설정 읽기
 * config/data-service.yml 파일 필요
 * 파일 내용에 아래 설정 값 필요
 * telegram:
 *   token: 봇 파더 챗 Token
 *   chat_id: 봇파더 챗 ID
 * @author ccsweets
 */
public class YmlConfig {
    public static Map<String, Object> getYmlMap(String key){
        try {

            String path =  Config.getConfig("data.service.yml.path", "config/data-service.yml");

            File file = new File(path);
            if(!file.isFile()){
                throw new IORuntimeException("yml file not found path: " + path);
            }

            Map<String, Object> propMap = new Yaml().load(new FileReader(path));
            if(!propMap.containsKey(key)){
                throw new IORuntimeException("yml key contains false key: " + key);
            }

            //noinspection unchecked
            return (Map<String, Object>) propMap.get(key);
        }catch(IOException e){
            throw new IORuntimeException(e);
        }
    }
}
