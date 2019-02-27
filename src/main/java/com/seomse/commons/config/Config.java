/** 
 * <pre>
 *  파 일 명 : Config.java
 *  설    명 : 설정
 *  		  
 *  			                  
 *  작 성 자 : macle
 *  작 성 일 : 2017.07
 *  버    전 : 1.1
 *  수정이력 : 2019.02 
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017, 2019 by ㈜섬세한사람들. All right reserved.
 */

package com.seomse.commons.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seomse.commons.handler.ExceptionHandler;
import com.seomse.commons.meta.MetaData;
import com.seomse.commons.meta.MetaDataManager;
import com.seomse.commons.utils.ExceptionUtil;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class Config {
	private final static Logger logger = LoggerFactory.getLogger(Config.class);
	

	private static final Config instance = new Config();
	
	/**
	 * 예외 핸들러 설정
	 * @param exceptionHandler
	 */
	public static void setExceptionHandler(ExceptionHandler exceptionHandler) {
		instance.exceptionHandler = exceptionHandler;
	}
	
	/**
	 * 설정값 얻기
	 * @param key 설정키
	 * @return
	 */
	public static String getConfig(String key){
		return instance.getConfigValue(key);
	}
	
	/**
	 * 설정값 얻기
	 * @param key 설정키
	 * @param defaultValue 기본값
	 * @return
	 */
	public static String getConfig(String key, String defaultValue){
		return instance.getConfigValue(key, defaultValue);
	}
	
	/**
	 * 설정값 세팅
	 * @param key
	 * @param value
	 */
	public static void setConfig(String key, String value){
		instance.setConfigValue(key, value);
	}
	
	

	/**
	 * 설정값 얻기 Long형
	 * @param key 설정키
	 * @return 
	 */
	public static Long getLong(String key){
		return getLong(key, null);
	}
	
	/** 
	 * 설정값 얻기 Long형
	 * @param key 설정키
	 * @param defaultValue 기본값
	 * @return 
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
			ExceptionUtil.exception(e, logger, instance.exceptionHandler);
		}
		
		return defaultValue;
	}
	
	
	/**
	 * 설정값 얻기 Integer형
	 * @param key 설정키
	 * @return
	 */
	public static Integer getInteger(String key){
		return getInteger(key, null);
	}
	
	/** 
	 * 설정값 얻기 Integer형
	 * @param key 설정키
	 * @param defaultValue 기본값
	 * @return 
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
			ExceptionUtil.exception(e, logger, instance.exceptionHandler);
		}
		
		return defaultValue;
	}
	
	/** 
	 * 설정값 얻기 Double형
	 * @param key 설정키
	 * @return 
	 */
	public static Double getDouble(String key){
		return getDouble(key, null);
	}
	
	/** 
	 * 설정값 얻기 Double형
	 * @param key 설정키
	 * @param defaultValue 기본값
	 * @return 
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
			ExceptionUtil.exception(e, logger, instance.exceptionHandler);
		}
		
		return defaultValue;
	}
	
	
	/**
	 * 설정값 얻기 Boolean형
	 * @param key 설정키
	 * @return
	 */
	public static Boolean getBoolean(String key){
		return getBoolean(key, null);
	}
			
	/**
	 * 설정값 얻기 Boolean형
	 * @param key 설정키
	 * @param defaultValue 기본값
	 * @return
	 */
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
			logger.error("config value error (N,Y) or (true, false) -> " + resultValue);
			return defaultValue; 
		}	
	}

	/**
	 * 옵져버 추가 ( 설정정보 업데이트 내역 )
	 * @param configObserver
	 */
	public static void addObserver(ConfigObserver configObserver){
		synchronized (instance.observerLock) {
			instance.observerList.add(configObserver);
		}
	}
	
	/**
	 * 옵져버 제거 ( 설정정보 업데이트 내역 )
	 * @param configObserver
	 */
	public static void removeObserver(ConfigObserver configObserver){
		synchronized (instance.observerLock) {
			instance.observerList.remove(configObserver);
		}
	}
	
	/**
	 * 설정파일 경로설정
	 * @param configPath
	 */
	public static void setConfigPath(String configPath){
		instance.configPath = configPath;
		
		instance.loadConfigFile(true);
	}
	
	/**
	 * 업데이트 테이블 정보설정
	 * @param configConnection
	 * @param tableName
	 * @param keyColumn
	 * @param valueColumn
	 * @param timeColumn
	 */
	public static void setTableInfo(final ConfigConnection configConnection, final String tableName, 
			final String keyColumn,  final String valueColumn, final String timeColumn){
		instance.setTableInformation(configConnection, tableName, keyColumn, valueColumn, timeColumn);
	}
	
	/**
	 * 로그백 경로설정
	 * @param configPath
	 */
	public static void setLogbackConfigPath(String configPath){
		setLogbackConfigPath(configPath, true);
	}
	
	
	
	
	/**
	 * 설정정보 업데이트
	 * DB테이블에 가저온졍보 이후시간이 업데이트된경우
	 */
	public static void update(){
		instance.metaUpdate();
	}
	
	
	/**
	 * 로그백 설정파일 경로설정
	 * isErrorLog는 초기생성자에서 에러를 출력하지않기위한 로그
	 * @param configPath
	 * @param isErrorLog 
	 */
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
	    		ExceptionUtil.exception(je, logger, instance.exceptionHandler);
	    	}
	    }
	}
	
	
	
	private String configPath = "config/seomse_config.xml";
	
	
	
	private Map<String, String> configMap = new HashMap<String, String>();

	private Object configLock = new Object();
	
	
	private List<ConfigObserver> observerList = new ArrayList<ConfigObserver>();
	private Object observerLock = new Object();
	private Object notifyLock = new Object();
	
	
	private Object tableInfoLock = new Object();
	
	private ExceptionHandler exceptionHandler;
	
	/**
	 * 생성자
	 * 싱글턴글래스
	 */
	private Config(){
		

		
		String defaultLogbackPath = "config/logback.xml";
		File logbackFile = new File(defaultLogbackPath);
		if(logbackFile.exists()){
			//기본경로에 로그백 설정파일이존재할경우 호출
			setLogbackConfigPath(defaultLogbackPath, false);
		}
		
		File file = new File(configPath);
		
		if(file.exists()){
			//기본경로에 파일이존재할경우 호출
			//초기설정파일에는 에러로그를 쓰지않음 (설정파일이 없어도 허용)
			loadConfigFile(false);
		}
	}
	
	
	
	
	/**
	 * 설정파일 로드
	 */
	public void loadConfigFile(){		
		loadConfigFile(true);
	}
	
	/**
	 * 설정파일 로드
	 * isErrorLog는 초기생성자에서 에러를 출력하지않기위한 로그
	 * @param isErrorLog
	 */
	private void loadConfigFile(boolean isErrorLog){		
		InputStream configInputStream = null;
		try{
			//설정업데이트 정보 옵져버 전달용
			
			configInputStream = new FileInputStream(new File(configPath));
			Properties props = new Properties();
			props.loadFromXML(configInputStream);
			
			List<ConfigInfo> infoList = new ArrayList<ConfigInfo>();
			
			Set<Object> keySet =props.keySet();
			for(Object key : keySet){
				String keyValue  = (String) key;
				
				String value = props.getProperty(keyValue);
				if(value == null){
					continue;
				}
				ConfigInfo info = new ConfigInfo();
				info.setKey(keyValue);
				info.setValue(value);
				infoList.add(info);

			}
			setConfigValue(infoList);
			infoList.clear();
	
			
		}catch(Exception e){
			//초기설정에는 에러로그를 호출하지않음
			if(isErrorLog){
				ExceptionUtil.exception(e, logger, exceptionHandler);

			}
		}
		
		
		try{
			if(configInputStream != null){
				configInputStream.close(); configInputStream =null;
			}
		}catch(Exception e){}
	}
	
	
	/**
	 * 설정값 얻기
	 * @param key
	 * @return
	 */
	private String getConfigValue(String key){
		synchronized (configLock) {
			//락구간최소화 신경쓰기 
			return configMap.get(key);
		}
	}
	

	/**
	 * 설정값 얻기
	 * @param key 설정키
	 * @param defaultValue 기본값
	 * @return
	 */
	private String getConfigValue(String key, String defaultValue){
		synchronized (configLock) {
			String value =configMap.get(key);
			if(value == null){
				return defaultValue;
			}
			return value;
		}
	}
	/**
	 * 설정값 세팅
	 * @param key
	 * @param value
	 */
	private void setConfigValue(String key, String value){
		if(value == null){
			return ;
		}
		boolean isUpdate = false;
		synchronized (configLock) {
			String lastValue = configMap.get(key);
		
			if(lastValue == null){
				isUpdate = true;
			}else{
				if(!lastValue.equals(value)){
					isUpdate = true;
				}
			}
			
			if(isUpdate){
				configMap.put(key, value);
			}
		}
		if(isUpdate){
			Map<String, String> updateConfigMap = new HashMap<String, String>();
			updateConfigMap.put(key, value);
			notifyConfig(updateConfigMap);
		}
	

	}
	
	private void setConfigValue(List<ConfigInfo> infoList){
		
		if(infoList == null || infoList.size() == 0){
			return ;
		}
		
		Map<String, String> updateConfigMap = new HashMap<String, String>();
		synchronized (configLock) {
			//설정정보 업데이트구간
			for(ConfigInfo info : infoList){
				boolean isUpdate = false;
				
				String lastValue = configMap.get(info.getKey());
				if(lastValue == null){
					isUpdate = true;
				}else{
				
					if(!lastValue.equals(info.getValue())){
						isUpdate = true;
					}
				}
				
				if(isUpdate){
					configMap.put(info.getKey(), info.getValue());
					updateConfigMap.put(info.getKey(), info.getValue());
				}
			}
			
		}
		notifyConfig(updateConfigMap);
		
	}
	/**
	 * 설정변경정보 알림
	 * @param updateConfigMap
	 */
	private void notifyConfig(Map<String, String> updateConfigMap){
		if(updateConfigMap == null || updateConfigMap.size() ==0){
			return;
		}
		
		Set<String> keySet = updateConfigMap.keySet();
		for(String key : keySet){
			logger.trace("Confg update key: " + key + " value: " + updateConfigMap.get(key));
		}
		ConfigObserver [] configObserverArray = null; 
		synchronized (observerLock) {
			configObserverArray = observerList.toArray(new ConfigObserver[observerList.size()]);
		}
		
		synchronized (notifyLock) {
			for(ConfigObserver configObserver : configObserverArray){
				configObserver.updateConfig(updateConfigMap);
			}	
		}
	}
	
	//설정 테이블 정보
	private long dateTime = 0;
	private MetaData metaData = null;
	
	/**
	 * 설정정보 업데이트
	 * DB접속 이용
	 */
	public void metaUpdate(){
		if(metaData == null){
			return ;
		}
		try{
			metaData.update();
		}catch(Exception e){
			ExceptionUtil.exception(e, logger, exceptionHandler);
		}
	}
	
	/**
	 * 테이블 접속정보설정
	 */
	private void setTableInformation(final ConfigConnection configConnection, final String tableName, 
			final String keyColumn,  final String valueColumn, final String timeColumn){

	
		synchronized (tableInfoLock) {
			
		
			MetaDataManager metaDataManager  = MetaDataManager.getInstance();
			if(metaData != null){
				metaDataManager.removeMetaData(metaData);
			}
			this.dateTime = 0l;
			metaData = new MetaData() {
				
				public void update() {
					PreparedStatement pstmt = null;
					ResultSet result = null;
					try{
						StringBuilder sqlBuilder = new StringBuilder();
						
						sqlBuilder.append("SELECT " + keyColumn + ", " + valueColumn ); 
						
						if(timeColumn != null){
							sqlBuilder.append(", " + timeColumn ); 
						}
						sqlBuilder.append(" FROM " + tableName); 
						if(dateTime > 0l){
							sqlBuilder.append(" WHERE " + timeColumn + " > ?" );
						}
						
						Connection conn = configConnection.getConnection();
						pstmt = conn.prepareStatement(sqlBuilder.toString());
						if(dateTime > 0l){
							Timestamp timestamp = new Timestamp(dateTime);
							pstmt.setTimestamp(1, timestamp);
						}
						List<ConfigInfo> infoList = new ArrayList<ConfigInfo>();
						
						result = pstmt.executeQuery();	
						while(result.next()){
							String key = result.getString(keyColumn);
							String value = result.getString(valueColumn);
							
							if(key == null || value == null){
								continue;
							}
							
							ConfigInfo info = new ConfigInfo();
							info.setKey(key);
							info.setValue(value);
							infoList.add(info);
							
							if(timeColumn != null){
								Timestamp timeStamp = result.getTimestamp(timeColumn);
								long time = timeStamp.getTime();
								if(dateTime < time){
									dateTime = time;
								}
							}
							
						}
						setConfigValue(infoList);
						infoList.clear();
						infoList= null;
					}catch(Exception e){
						ExceptionUtil.exception(e, logger, exceptionHandler);
					}finally {
						if(pstmt != null){try{pstmt.close(); pstmt = null;}catch(Exception e){}}
						if(result != null){try{result.close(); result = null;}catch(Exception e){}}
					}
				}
				
				public String getName() {
					return Config.class.getName();
				}
				
			};
			
			//설정정보는 항상 최우선으로 업데이트 되어야함
			metaDataManager.addMetaData( 0, metaData);
		}
	}
	
	/**
	 * 테스트소스
	 * @param args
	 */
	public static void main(String [] args){



		System.out.println(getConfig("seomse.jdbc.type"));

//		setLogbackConfigPath("config/logback.xml");
//		System.out.println(	Config.getConfig("application.database.user.name"));
//		setConfig("test", "tt");
//		System.out.println(	Config.getConfig("test"));
//		System.out.println(	Config.class.getName());
	
		
	}
}

