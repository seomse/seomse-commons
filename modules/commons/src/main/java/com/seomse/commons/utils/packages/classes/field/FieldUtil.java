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


package com.seomse.commons.utils.packages.classes.field;

import com.seomse.commons.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;


/**
 * reflect field 유틸
 * @author macle
 */
@Slf4j
public class FieldUtil {

	/**
	 * 상속한 모든부모의 필드까지 얻기 (상속에 상속에 상속 구조)
	 * @param classes class
	 * @return filed [] filed array
	 */
	public static Field [] getFieldArrayToAllParents(Class<?> classes){
		Field [] fields = classes.getDeclaredFields();
		
		if(classes.getSuperclass() != Object.class){
			Field [] tempFields = fields;
			Field [] superFields = classes.getSuperclass().getDeclaredFields();
			fields = mergeField(superFields, tempFields);
			
			return getFieldArrayToAllParents(classes.getSuperclass(), fields);
			
		}
		
		return fields;
	}
	
	/**
	 * 상속한 모든부모의 필드까지 얻기 (상속에 상속에 상속 구조)
	 * @param classes class
	 * @param fields field []
	 * @return field []
	 */
	private static Field [] getFieldArrayToAllParents(Class<?> classes, Field [] fields){
		if(classes.getSuperclass() != Object.class){
			Field [] tempFields = fields;
			Field [] superFields = classes.getSuperclass().getDeclaredFields();
			fields = mergeField(superFields, tempFields);
			return getFieldArrayToAllParents(classes.getSuperclass(), fields);
		}
		
		return fields;
	}
	
	/**
	 * 자신의 상의부모 필드까지 얻기
	 * @param classes class
	 * @return filed []
	 */
	public static Field [] getFieldArrayToParents(Class<?> classes){
		Field [] fields = classes.getDeclaredFields();
		
		if(classes.getSuperclass() != Object.class){
			Field [] tempFields = fields;
			Field [] superFields = classes.getSuperclass().getDeclaredFields();
			fields = mergeField(superFields, tempFields);
		}
		
		return fields;
	}

	/**
	 * merge filed
	 * @param fields filed []
	 * @param mergeFields filed []
	 * @return filed []
	 */
	public static Field [] mergeField(Field [] fields, Field [] mergeFields){
		Field [] newFields = new Field[fields.length + mergeFields.length];

		int i=0;

		for(Field field : fields){
			newFields[i] = field;
			i++;
		}

		for(Field field : mergeFields){
			newFields[i] = field;
			i++;
		}

		return newFields;
	}

	/**
	 * 같은필드가있을경우 내부데이터를 복사
	 * @param originalObject originalObject
	 * @param copyObject copyObject
	 */
	public static void copy(Object originalObject, Object copyObject){
		Field [] objectFieldArray = getFieldArrayToParents(originalObject.getClass());
		
		Field [] copyFieldArray = getFieldArrayToParents(copyObject.getClass());
		
		for(Field copyField : copyFieldArray){
			
			for(Field objectField : objectFieldArray){
				if(copyField.getName().equals(objectField.getName())){
					copyField.setAccessible(true);
					objectField.setAccessible(true);
					
					try {
						copyField.set(copyObject, objectField.get(originalObject));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						log.error(ExceptionUtil.getStackTrace(e));
					}

				}
			}
		}
	}
	
}