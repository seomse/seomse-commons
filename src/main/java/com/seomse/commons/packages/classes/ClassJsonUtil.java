

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


/**
 * <pre>
 *  파 일 명 : ClassJsonUtil.java
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
public class ClassJsonUtil {

	private static final Logger logger = LoggerFactory.getLogger(ClassJsonUtil.class);

	
	public static final String CLASS_NAME = "classFullName^";

	/**
	 * jsonString 을 List<T> 생성
	 * @param jsonString jsonString
	 * @return List<T>
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



			JSONObject checkObject = jSONArray.getJSONObject(0);
			String className = checkObject.getString(CLASS_NAME);
			//noinspection deprecation
			T chkT =(T)Class.forName(className).newInstance();
			Field []  fields= FieldUtil.getFieldArrayToParents(chkT.getClass());

			List<T> objectList = new ArrayList<>();
			for(int i=0; i<size ; i++){
				JSONObject jSONObject = jSONArray.getJSONObject(i);
				jSONObject.remove(CLASS_NAME);

				//noinspection deprecation
				T t =(T)Class.forName(className).newInstance();
				for(Field field : fields){
					setField(t, field, jSONObject);
				}
			
				objectList.add(t);
			}
			
			return objectList;
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static <T> void setField(T t, Field field, JSONObject jSONObject) throws IllegalAccessException {
		field.setAccessible(true);
		if(!jSONObject.isNull(field.getName())){
			Object obj  = jSONObject.get(field.getName() );
			if(obj.getClass() == org.json.JSONObject.class){
				Map dataMap = new HashMap();

				JSONObject mapObject =(JSONObject) obj;
				//noinspection unchecked
				Iterator<String> mapIterator = mapObject.keys();
				while(mapIterator.hasNext()){
					String key = mapIterator.next();
					//noinspection unchecked
					dataMap.put(key, mapObject.get(key));
				}

				field.set(t,  dataMap);
			}else{
				field.set(t,  obj);
			}

		}
	}



	/**
	 * jsonString 을 활용한 클래스 생성
	 * @param jsonString jsonString
	 * @return  <T>
	 */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T makeObject(String jsonString){
		
		try {

			JSONObject jSONObject = new JSONObject(jsonString);
			
			String className = jSONObject.getString(CLASS_NAME);	
			jSONObject.remove(CLASS_NAME);

			//noinspection deprecation
			T t =(T)Class.forName(className).newInstance();
			Field []  fields= FieldUtil.getFieldArrayToParents(t.getClass());

			for(Field field : fields){
				setField(t, field, jSONObject);
			}

			return t;
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	

	/**
	 * T to  JsonString
	 * 리스트에는 단순 변수를 사용한다.
	 * @param classList JsonString
	 * @return JsonString
	 */
	public static <T> String objectListToJsonString(List<T> classList){
		if(classList == null || classList.size() ==0)
			return null;
		
		List<JSONObject> jsonObjectList = new ArrayList<>();
				
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
			throw new RuntimeException(e);
		}
		
		return org.json.simple.JSONArray.toJSONString(jsonObjectList);
	}
	
	

	/**
	 * object json 스트링으로 변환
	 * @param object object
	 * @return JsonString
	 */
	public static <T> String objectToJsonString(T object){
		if(object == null){
			return null;
		}
		
		try {
			Field []  fields = FieldUtil.getFieldArrayToParents(object.getClass());
			
			Map<String, Object> objectMap = new HashMap<>();
			objectMap.put(CLASS_NAME, object.getClass().getName());
			for(Field field : fields){
				field.setAccessible(true);
				Object obj =  field.get(object);
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
			
			List<Map<String, Object>> mapDataList = new ArrayList<>();
			for(int i=0; i<size ; i++){
				Map<String, Object> dataMap = new HashMap<>();
				
				JSONObject jSONObject = jSONArray.getJSONObject(i);

				setMapData(jSONObject, dataMap);
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
	 * @param jsonString
	 * @return
	 */
	public static Map<String, Object> makeDataMap(String jsonString){
		
		try {

			if(jsonString == null || jsonString.length() ==0)
				return Collections.emptyMap();
					
			JSONObject jSONObject = new JSONObject(jsonString);
	
			Map<String, Object> dataMap = new HashMap<>();

			setMapData(jSONObject, dataMap);
			return dataMap;
		
		} catch (Exception e) {
			logger.info(jsonString);
			logger.error(ExceptionUtil.getStackTrace(e));
			return Collections.emptyMap();
		}

	}


	private static void setMapData(JSONObject jSONObject, Map<String, Object> dataMap){
		Iterator keyIterator = jSONObject.keys();
		while(keyIterator.hasNext()){
			String key = (String)keyIterator.next();

			if(jSONObject.isNull(key)){
				dataMap.put(key, null);
			}else{
				dataMap.put(key, jSONObject.get(key));
			}
		}
	}

	/**
	 * json문자열을 정렬된 dataMap 형태로 생성해서 돌려준다.
	 * @param jsonString
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
	 * @param mapData
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