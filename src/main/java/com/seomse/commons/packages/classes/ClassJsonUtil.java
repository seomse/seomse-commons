/** 
 * <pre>
 *  파 일 명 : PackageSearch.java
 *  설    명 : 클래스와 스트링을 Json객체를이용하여 서로변환
 *         
 *  작 성 자 : macle
 *  작 성 일 : 2017.09
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */

package com.seomse.commons.packages.classes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.seomse.commons.packages.classes.field.FieldUtil;
import com.seomse.commons.utils.ExceptionUtil;



public class ClassJsonUtil {

	private static final Logger logger = LoggerFactory.getLogger(ClassJsonUtil.class);

	
	public static final String CLASS_NAME = "classFullName^";
	
	
	
	/**
	 * json문자열을 클래스로 생성하여 리스트로 돌려준다.
	 * @param valu1e
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> makeObjectList(String jsonString){
		
		try {
			if(jsonString == null || jsonString.length() ==0)
				return null;
		
			JSONArray jSONArray = new JSONArray(jsonString);
			
			int size = jSONArray.length();
			
			if(size == 0 ){
				return Collections.emptyList();
			}
			
			List<T> objectList = new ArrayList<T>();
			JSONObject checkObject = jSONArray.getJSONObject(0);
			String className = checkObject.getString(CLASS_NAME);	
			T chkT =(T)Class.forName(className).newInstance();
			Field []  fields= FieldUtil.getFieldArrayToParents(chkT.getClass());
			
			
			for(int i=0; i<size ; i++){
				JSONObject jSONObject = jSONArray.getJSONObject(i);
				jSONObject.remove(CLASS_NAME);
				
				T t =(T)Class.forName(className).newInstance();
				for(Field field : fields){
					field.setAccessible(true);
					if(!jSONObject.isNull(field.getName())){
						Object obj  = jSONObject.get(field.getName() );
						if(obj.getClass() == org.json.JSONObject.class){
							Map dataMap = new HashMap();
							
							JSONObject mapObject =(JSONObject) obj;
							Iterator<String> mapIterator = mapObject.keys();
							while(mapIterator.hasNext()){
								String key = mapIterator.next();
								dataMap.put(key, mapObject.get(key));
							}
							
							field.set(t,  dataMap);
						}else{
							field.set(t,  obj);
						}	
						
						
//						field.set(t,  jSONObject.get(field.getName() ));
					}
					
				}
			
				objectList.add(t);
			}
			
			return objectList;
		
		} catch (Exception e1) {
			logger.error(ExceptionUtil.getStackTrace(e1));
			return null;
		}

	}
	

	/**
	 * json문자열을 클래스로 생성하여 리스트로 돌려준다.
	 * @param value
	 * @return
	 */
	public static <T> T makeVo(String jsonString){
		return makeObject(jsonString);
	}
	
	/**
	 * json문자열을 클래스로 생성하여 리스트로 돌려준다.
	 * @param value
	 * @return
	 */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T makeObject(String jsonString){
		
		try {

			JSONObject jSONObject = new JSONObject(jsonString);
			
			String className = jSONObject.getString(CLASS_NAME);	
			jSONObject.remove(CLASS_NAME);
			
		
			T t =(T)Class.forName(className).newInstance();
			Field []  fields= FieldUtil.getFieldArrayToParents(t.getClass());
			

			for(Field field : fields){
				field.setAccessible(true);
				if(!jSONObject.isNull(field.getName())){
					Object obj  = jSONObject.get(field.getName() );
					if(obj.getClass() == org.json.JSONObject.class){
						Map dataMap = new HashMap();
						
						JSONObject mapObject =(JSONObject) obj;
						Iterator<String> mapIterator = mapObject.keys();
						while(mapIterator.hasNext()){
							String key = mapIterator.next();
							dataMap.put(key, mapObject.get(key));
						}
						
						field.set(t,  dataMap);
					}else{
						field.set(t,  obj);
					}	
				}
			}
			
			
			return t;
		
		} catch (Exception e1) {
			logger.error("ERROR JSONString : " + jsonString);
			logger.error(ExceptionUtil.getStackTrace(e1));
			return null;
		}

	}

	

	/**
	 * 리스트에있는 클래스를 Json스트링으로 만든다
	 * 리스트에는 단순 변수를 사용한다.
	 * @param classList
	 * @return
	 */
	public static <T> String voListToJsonString(List<T> classList){
		return objectListToJsonString(classList);
	}
	/**
	 * 리스트에있는 클래스를 Json스트링으로 만든다
	 * 리스트에는 단순 변수를 사용한다.
	 * @param classList
	 * @return
	 */
	public static <T> String objectListToJsonString(List<T> classList){
		if(classList == null || classList.size() ==0)
			return null;
		
		List<JSONObject> jsonObjectList = new ArrayList<JSONObject>();
				
		Class<?> voClass = classList.get(0).getClass();
		Field []  fields= FieldUtil.getFieldArrayToParents(voClass);

		try {
			for(T voObject : classList){
				JSONObject jSONObject = new JSONObject();
				jSONObject.put(CLASS_NAME, voClass.getName());
				
				for(Field field : fields){
					field.setAccessible(true);
					jSONObject.put(field.getName(), field.get(voObject));
				
				}
				
				jsonObjectList.add(jSONObject);
			}
			
		} catch (Exception e) {
			logger.error(ExceptionUtil.getStackTrace(e));
			return null;
		}
		
		return org.json.simple.JSONArray.toJSONString(jsonObjectList);
	}
	
	

	/**
	 * object객체를 json스트링으로 변환한다.
	 * @param objcet
	 * @return
	 */
	public static <T> String objectToJsonString(T objcet){
		if(objcet == null){
			return null;
		}
		
		try {
			Field []  fields = FieldUtil.getFieldArrayToParents(objcet.getClass());
			
			Map<String, Object> objectMap = new HashMap<String, Object>();
			objectMap.put(CLASS_NAME, objcet.getClass().getName());
			for(Field field : fields){
				field.setAccessible(true);
				Object obj =  field.get(objcet);
				if(obj != null){
					objectMap.put(field.getName(), obj);
				}
			}
				
			return 	org.json.simple.JSONObject.toJSONString(objectMap);
		
		} catch (Exception e) {
			logger.error(ExceptionUtil.getStackTrace(e));
			
			return null;
		}

	}
	
	/**
	 * ListMap의 형태를 JsonString 으로 출력한다.
	 * @param mapDataList
	 * @return
	 */
	public static String mapDataListToJsonString(List<Map<String, Object>> mapDataList){
		if(mapDataList == null || mapDataList.size() ==0)
			return null;
		
		try {
			
			return org.json.simple.JSONArray.toJSONString(mapDataList);
		}catch(Exception e){
			logger.error(ExceptionUtil.getStackTrace(e));
			return null;
		}
	}
	
	/**
	 * json 문자열을 dataMapList형테로 가져온다.
	 * @param jsonString
	 * @return
	 */
	public static List<Map<String, Object>> makeListMap(String jsonString){
		
		try {
						
			if(jsonString == null || jsonString.length() ==0)
				return Collections.emptyList();
		
			JSONArray jSONArray = new JSONArray(jsonString);
			
			int size = jSONArray.length();
			if(size == 0 ){
				return Collections.emptyList();
			}
			
			List<Map<String, Object>> mapDataList = new ArrayList<Map<String, Object>>();
			for(int i=0; i<size ; i++){
				Map<String, Object> dataMap = new HashMap<String, Object>();
				
				JSONObject jSONObject = jSONArray.getJSONObject(i);
	
				@SuppressWarnings("rawtypes")
				Iterator keyIterator = jSONObject.keys();
				while(keyIterator.hasNext()){
					String key = (String)keyIterator.next();
					if(jSONObject.isNull(key)){
						
						dataMap.put(key, null);
					}else{
						dataMap.put(key, jSONObject.get(key));
					}
					
				}
				mapDataList.add(dataMap);
			}
			
			return mapDataList;
		
		} catch (Exception e) {
			logger.error(ExceptionUtil.getStackTrace(e));
			return null;
		}

	}
	

	/**
	 * json문자열을 dataMap 형태로 생성해서 돌려준다.
	 * @param value
	 * @return
	 */
	public static Map<String, Object> makeDataMap(String jsonString){
		
		try {

			if(jsonString == null || jsonString.length() ==0)
				return Collections.emptyMap();
					
			JSONObject jSONObject = new JSONObject(jsonString);
	
			Map<String, Object> dataMap = new HashMap<String, Object>();

			@SuppressWarnings("rawtypes")
			Iterator keyIterator = jSONObject.keys();
			while(keyIterator.hasNext()){
				String key = (String)keyIterator.next();
			
				if(jSONObject.isNull(key)){
					dataMap.put(key, null);
				}else{
					dataMap.put(key, jSONObject.get(key));
				}
			}
			
			return dataMap;
		
		} catch (Exception e) {
			logger.info(jsonString);
			logger.error(ExceptionUtil.getStackTrace(e));
			return Collections.emptyMap();
		}

	}
	
	/**
	 * json문자열을 정렬된 dataMap 형태로 생성해서 돌려준다.
	 * @param value
	 * @return
	 */
	public static Map<String, Object> makeOrderedDataMap(String jsonString){
		
		try {

			if(jsonString == null || jsonString.length() ==0)
				return Collections.emptyMap();
			
			@SuppressWarnings("unchecked")
			Map<String, Object> dataMap  = new Gson().fromJson(jsonString, LinkedHashMap.class);

			return dataMap;
		
		} catch (Exception e) {
			logger.info(jsonString);
			logger.error(ExceptionUtil.getStackTrace(e));
			return Collections.emptyMap();
		}

	}
	
	/**
	 * ListMap의 형태를 JsonString 으로 출력한다.
	 * @param mapDataList
	 * @return
	 */
	public static String mapDataToJsonString(Map<String, Object> mapData){
		if(mapData == null || mapData.size() ==0)
			return null;
		
		try {
			return org.json.simple.JSONObject.toJSONString(mapData);
		}catch(Exception e){
			logger.error(ExceptionUtil.getStackTrace(e));
			return null;
		}
	}
	

	
}