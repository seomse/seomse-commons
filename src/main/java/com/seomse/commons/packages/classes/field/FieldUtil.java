/** 
 * <pre>
 *  파 일 명 : FieldUtil.java
 *  설    명 : 필드를 사용할때 필요한 유틸모음
 *         
 *  작 성 자 : macle
 *  작 성 일 : 2017.09
 *  버    전 : 1.0
 *  수정이력 : 
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */

package com.seomse.commons.packages.classes.field;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seomse.commons.utils.ExceptionUtil;

public class FieldUtil {
	
		
	private static final Logger logger = LoggerFactory.getLogger(FieldUtil.class);

	
	/**
	 * 상속한 모든부모의 필드까지 얻기 (상속에 상속에 상속 구조)
	 * @param classes
	 * @return
	 */
	public static Field [] getFieldArrayToAllParents(Class<?> classes){
		Field [] fields = classes.getDeclaredFields();
		
		if(classes.getSuperclass() != Object.class){
			Field [] tempFields = fields;
			Field [] superFields = classes.getSuperclass().getDeclaredFields();
			fields = new Field[superFields.length + tempFields.length];
			int i=0;
			
			for(Field field : superFields){
				fields[i] = field;
				i++;
			}
			for(Field field : tempFields){
				fields[i] = field;
				i++;
			}
			
			return getFieldArrayToAllParents(classes.getSuperclass(), fields);
			
		}
		
		return fields;
	}
	
	/**
	 * 상속한 모든부모의 필드까지 얻기 (상속에 상속에 상속 구조)
	 * @param classes
	 * @param fields
	 * @return
	 */
	private static Field [] getFieldArrayToAllParents(Class<?> classes, Field [] fields){
		if(classes.getSuperclass() != Object.class){
			Field [] tempFields = fields;
			Field [] superFields = classes.getSuperclass().getDeclaredFields();
			fields = new Field[superFields.length + tempFields.length];
			int i=0;
			
			for(Field field : superFields){
				fields[i] = field;
				i++;
			}
			for(Field field : tempFields){
				fields[i] = field;
				i++;
			}
			return getFieldArrayToAllParents(classes.getSuperclass(), fields);
		}
		
		return fields;
	}
	
	/**
	 * 자신의 상의부모 필드까지 얻기
	 * @param classes
	 * @return
	 */
	public static Field [] getFieldArrayToParents(Class<?> classes){
		Field [] fields = classes.getDeclaredFields();
		
		if(classes.getSuperclass() != Object.class){
			Field [] tempFields = fields;
			Field [] superFields = classes.getSuperclass().getDeclaredFields();
			fields = new Field[superFields.length + tempFields.length];
			int i=0;
			
			for(Field field : superFields){
				fields[i] = field;
				i++;
			}
			for(Field field : tempFields){
				fields[i] = field;
				i++;
			}
			
		}
		
		return fields;
	}
	
	/**
	 * 같은필드가있을경우 내부데이터를 복사
	 * @param originalObject
	 * @param copyObject
	 */
	public static void copy(Object originalObject, Object copyObject){
		Field [] objectFieldArray = getFieldArrayToParents(originalObject.getClass());
		
		Field [] copyFieldArray = getFieldArrayToParents(copyObject.getClass());
		
		for(Field copyField : copyFieldArray){
			
			for(Field objectField : objectFieldArray){
				if(copyField.getName().equals(objectField.getName()) && objectField.getClass() == copyField.getClass()){
					copyField.setAccessible(true);
					objectField.setAccessible(true);
					
					try {
						copyField.set(copyObject, objectField.get(originalObject));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						logger.error(ExceptionUtil.getStackTrace(e));
					}
				}
			}
		}
	}
	
}