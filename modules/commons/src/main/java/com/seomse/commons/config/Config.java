/*
 * Copyright (C) 2020 Seomse Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.seomse.commons.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.seomse.commons.handler.ExceptionHandler;
import com.seomse.commons.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/**
 * 설정
 * 여러 설정의 우선순위를 사용하여 설정을 얻을 수 있음
 * 시스템 프로퍼티
 * xml파일
 * database에 저장된 설정정보등 설정정보에 대한 우선순위를 활용할때 유용함
 * @author macle
 */
@Slf4j
public class Config {


	private static final Config instance = new Config();

	/**
	 * 예외 핸들러 설정
	 * @param exceptionHandler ExceptionHandler
	 */
	public static void setExceptionHandler(ExceptionHandler exceptionHandler) {
		instance.exceptionHandler = exceptionHandler;
	}

	/**
	 * 설정값 얻기
	 * @param key String 설정키
	 * @return String config value
	 */
	public static String getConfig(String key){
		return instance.getConfigValue(key);
	}

	/**
	 * 설정값 얻기
	 * @param key String 설정키
	 * @param defaultValue String 기본값
	 * @return String config value
	 */
	public static String getConfig(String key, String defaultValue){
		return instance.getConfigValue(key, defaultValue);
	}

	/**
	 * 설정값 세팅
	 * @param key String key
	 * @param value String value
	 */
	public static void setConfig(String key, String value){
		instance.setConfigValue(key, value);
	}

	/**
	 * 설정값 얻기 Long형
	 * @param key String 설정키
	 * @return Long config value(long)
	 */
	public static Long getLong(String key){
		return getLong(key, null);
	}

	/**
	 * 설정값 얻기 Long형
	 * @param key String 설정키
	 * @param defaultValue Long 기본값
	 * @return Long config value(long)
	 */
	public static Long getLong(String key, Long defaultValue){
		String resultValue = instance.getConfigValue(key);
		if(resultValue == null){
			return defaultValue;
		}

		resultValue = resultValue.trim();
		try{
			return Long.parseLong(resultValue);
		}catch(Exception e){
			ExceptionUtil.exception(e, log, instance.exceptionHandler);
		}

		return defaultValue;
	}

	/**
	 * 설정값 얻기 Integer 형
	 * @param key String 설정키
	 * @return Integer config value(integer)
	 */
	public static Integer getInteger(String key){
		return getInteger(key, null);
	}

	/**
	 * 설정값 얻기 Integer 형
	 * @param key String 설정키
	 * @param defaultValue Integer 기본값
	 * @return Integer config value(integer)
	 */
	public static Integer getInteger(String key, Integer defaultValue){
		String resultValue = instance.getConfigValue(key);
		if(resultValue == null){
			return defaultValue;
		}

		resultValue = resultValue.trim();
		try{
			return Integer.parseInt(resultValue);
		}catch(Exception e){
			ExceptionUtil.exception(e, log, instance.exceptionHandler);
		}

		return defaultValue;
	}

	/**
	 * 설정값 얻기 Double 형
	 * @param key String 설정키
	 * @return Double config value(double)
	 */
	public static Double getDouble(String key){
		return getDouble(key, null);
	}

	/**
	 * 설정값 얻기 Double 형
	 * @param key String 설정키
	 * @param defaultValue Double 기본값
	 * @return Double config value(double)
	 */
	public static Double getDouble(String key, Double defaultValue){
		String resultValue = instance.getConfigValue(key);
		if(resultValue == null){
			return defaultValue;
		}

		resultValue = resultValue.trim();
		try{
			return Double.parseDouble(resultValue);
		}catch(Exception e){
			ExceptionUtil.exception(e, log, instance.exceptionHandler);
		}

		return defaultValue;
	}


	/**
	 * 설정값 얻기 Boolean 형
	 * @param key String 설정키
	 * @return Boolean config value(boolean)
	 */
	public static Boolean getBoolean(String key){
		return getBoolean(key, null);
	}

	/**
	 * 설정값 얻기 Boolean 형
	 * @param key String 설정키
	 * @param defaultValue Boolean 기본값
	 * @return Boolean config value(boolean)
	 */
	@SuppressWarnings("StringOperationCanBeSimplified")
	public static Boolean getBoolean(String key, Boolean defaultValue){
		String resultValue = instance.getConfigValue(key);
		if(resultValue == null){
			return defaultValue;
		}

		resultValue = resultValue.trim();

		if(resultValue.toUpperCase().equals("Y") || resultValue.toLowerCase().equals("true")){
			return true;
		}else if(resultValue.toUpperCase().equals("N") || resultValue.toLowerCase().equals("false")){
			return false;
		}else{
			log.error("config value error (N,Y) or (true, false) -> " + resultValue);
			return defaultValue;
		}
	}


    /**
     * 설정 정보 데이터 추가
     * synchronized 하지 않으므로 꼭 순서대로 동작하게 구현
     * 설정 우선순위 동작하므로 시스템 초기에 등록할 것
     * @param configData ConfigData(설정정보를 가지고 있는 객체)
     */
	public static void addConfigData(ConfigData configData){
        instance.addConfig(configData);
    }

	/**
	 * 옵져버 추가 ( 설정정보 업데이트 내역 )
	 * @param configObserver ConfigObserver
	 */
	public static void addObserver(ConfigObserver configObserver){
		synchronized (instance.observerLock) {
			instance.observerList.add(configObserver);
		}
	}

	/**8
	 * 옵져버 제거 ( 설정정보 업데이트 내역 )
	 * @param configObserver ConfigObserver
	 */
	public static void removeObserver(ConfigObserver configObserver){
		synchronized (instance.observerLock) {
			instance.observerList.remove(configObserver);
		}
	}

	/**
	 * 로그백 설정파일 경로설정
	 * isErrorLog 는 초기생성자에서 에러를 출력하지않기위한 로그
	 * @param configPath String logback xml path
	 * @param isErrorLog boolean isErrorLog
	 */
	@SuppressWarnings("SameParameterValue")
	private static void setLogbackConfigPath(String configPath, boolean isErrorLog){
		File file = new File(configPath);
		if(!file.isFile()){
			return ;
		}
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

		try {
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(context);
			context.reset();
			configurator.doConfigure(configPath);

		} catch (JoranException je) {
			if(isErrorLog){
				ExceptionUtil.exception(je, log, instance.exceptionHandler);
			}
		}
	}


	private final List<ConfigObserver> observerList = new ArrayList<>();
	private final Object observerLock = new Object();
	private final Object notifyLock = new Object();


	private final Object tableInfoLock = new Object();

	private ExceptionHandler exceptionHandler;

	private ConfigData [] configDataArray;


	private final Comparator<ConfigData> sort = Comparator.comparingInt(ConfigData::getPriority);

	/**
	 * 생성자
	 * 싱글턴글래스
	 */
	private Config(){

        String logbackPath = ConfigSet.LOG_BACK_PATH;
        File logbackFile = new File(logbackPath);
		if(logbackFile.isFile()){
			log.debug("logback path: " + logbackFile.getAbsolutePath());
			//기본경로에 로그백 설정파일이존재할경우 호출
			setLogbackConfigPath(logbackPath, false);
		}

		File file = new File( ConfigSet.CONFIG_PATH);
		if(!file.isFile()){
			file = new File( ConfigSet.CONFIG_DIR_PATH + "/seomse_config.xml");
		}


		List<ConfigData> configDataList = new ArrayList<>();
        XmlFileConfigData fileConfigData ;

        if(file.isFile()) {
			try {
				log.debug("config path: " + file.getAbsolutePath());
				fileConfigData = new XmlFileConfigData(file);
				configDataList.add(fileConfigData);
			} catch (Exception e) {
				log.error(ExceptionUtil.getStackTrace(e));

			}
		}
        if(ConfigSet.IS_SYSTEM_PROPERTIES_USE){
			configDataList.add(new SystemPropertiesData());
        }

		configDataArray = configDataList.toArray(new ConfigData[0]);

        if(configDataArray.length > 1){
			Arrays.sort(configDataArray, sort);
		}
    }


    private final Object addLock = new Object();

    private void addConfig(ConfigData configData){

		synchronized (addLock) {
			ConfigData[] newDataArray = new ConfigData[configDataArray.length + 1];
			System.arraycopy(configDataArray, 0, newDataArray, 0, configDataArray.length);
			newDataArray[newDataArray.length - 1] = configData;
			Arrays.sort(newDataArray, sort);
			this.configDataArray = newDataArray;
		}
    }


	/**
	 * 설정값 얻기
	 * @param key String 설정키
	 * @return String config value
	 */
	private String getConfigValue(String key){
		return getConfigValue(key , null);
	}


	/**
	 * 설정값 얻기
	 * @param key String 설정키
	 * @param defaultValue String 기본값
	 * @return String config value
	 */
	private String getConfigValue(String key, String defaultValue){
        ConfigData [] configDataArray = this.configDataArray;
        //순서정보를 명확히 하기위해 사용
        String value = null;
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i <configDataArray.length ; i++) {
            value = configDataArray[i].getConfig(key);

            if(value != null){
                break;
            }
        }
		if(value == null){
			return defaultValue;
		}
		return value;
	}

	/**
	 * 설정값 세팅
     * 최우선순위 설정값을 변경함
	 * @param key String 설정 키
	 * @param value String 설정 값
	 */
	private void setConfigValue(String key, String value){
		if(configDataArray.length == 0){
			synchronized (addLock){
				if(configDataArray.length == 0){
					ConfigData[] newDataArray = new ConfigData[1];
					ConfigDataImpl configDataImpl = new ConfigDataImpl();
					newDataArray [0] =  configDataImpl;
					this.configDataArray = newDataArray ;
				}
			}
		}
        configDataArray[0].setConfig(key, value);
	}

	static void notify(ConfigData configData, ConfigInfo [] configInfos){
        if(configInfos == null || configInfos.length ==0){
            return;
        }
        ConfigData firstData = instance.configDataArray[0];
		ConfigData [] configDataArray = instance.configDataArray;
		if(firstData == configData) {
			ConfigInfo [] changeInfos = new ConfigInfo[configInfos.length];


			//최우선순위 설정이 변경된 경우
			for (int i = 0; i <configInfos.length ; i++) {
				ConfigInfo configInfo = configInfos[i];
				if(configInfo.isDelete){
					String value = null;
					for(int j=1 ; j<configDataArray.length ; j++){
						value = configDataArray[j].getConfig(configInfo.key);
						if(value != null){
							break;
						}
					}
					if(value == null){
						changeInfos[i] = configInfo;
					}else{
						changeInfos[i] = new ConfigInfo(configInfo.key, value);
					}

				}else{
					changeInfos[i] = configInfo;
				}

			}

            instance.notifyConfig(changeInfos);
        }else{

			int dataIndex = configDataArray.length;
			for (int i = 0; i <configDataArray.length ; i++) {
				if(configData == configDataArray[i]){
					dataIndex = i;
					break;
				}
			}
			//등록된 데이터가 아니면
			if(dataIndex ==  configDataArray.length){
				return;
            }

			List<ConfigInfo> changeList = null ;
			outer:
			for(ConfigInfo configInfo :configInfos) {
				for (int i = 0; i < dataIndex; i++) {
					ConfigData leftData = configDataArray[i];
					if(leftData.containsKey(configInfo.key)){
						continue outer;
					}
				}

				if (changeList == null) {
                    changeList = new ArrayList<>();
				}

				if(configInfo.isDelete){
                    String value = null;
                    for(int j=dataIndex + 1 ; j<configDataArray.length ; j++){
                        value = configDataArray[j].getConfig(configInfo.key);
                        if(value != null){
                            break;
                        }
                    }
                    ConfigInfo changeInfo ;
                    if(value == null){
                        changeInfo = configInfo;
                    }else{
                        changeInfo = new ConfigInfo(configInfo.key, value);
                    }
                    changeList.add(changeInfo);
                }else{
                    changeList.add(configInfo);
                }
			}
            if(changeList != null){
                instance.notifyConfig(changeList.toArray(new ConfigInfo[0]));
            }
        }
    }

	/**
	 * 설정변경정보 알림
	 * @param changeInfos ConfigInfo [] 변경된 설정 정보
	 */
	private void notifyConfig(ConfigInfo [] changeInfos){
		if(changeInfos == null || changeInfos.length ==0){
			return;
		}

		for(ConfigInfo configInfo : changeInfos){
			log.trace("Config update key: " + configInfo.key + " value: " + configInfo.value);
		}
		ConfigObserver [] configObserverArray ;
		synchronized (observerLock) {
			configObserverArray = observerList.toArray(new ConfigObserver[0]);
		}

		synchronized (notifyLock) {
			for(ConfigObserver configObserver : configObserverArray){
				configObserver.updateConfig(changeInfos);
			}
		}
	}

}