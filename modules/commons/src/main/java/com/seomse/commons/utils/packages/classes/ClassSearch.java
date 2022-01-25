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


package com.seomse.commons.utils.packages.classes;

import com.seomse.commons.exception.ClassNotFoundRuntimeException;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class search
 * @author macle
 */
public class ClassSearch {
	
	private final String targetPath;
	
	/**
	 * 생성자
	 */
	public ClassSearch(){
		this.targetPath = this.getClass().getResource("/").getPath();
	}
	
	/**
	 * 생성자
	 * @param targetPath String path
	 */
	public ClassSearch(String targetPath){
		this.targetPath = targetPath;
	
	}

	/**
	 * 클래스패쓰얻기
	 * @return String class path
	 */
	public String getClassPath(){
		return this.getClass().getResource("/").getPath();
	}
	
	/**
	 * 패키지내의 클래스 목록 얻기
	 * file을 사용하므로 jar로 묶여있지 않아아햠.
	 * 클래스가 .jar로 묶여있지 않고 classes등의 폴더에 
	 * @param packageName String 패키지명
	 * @return List 클래스 리스트
	 */
	public List<Class<?>> getClassesForPackage(String packageName) {
		
	    //패키지 절대경로

		File directory ;
	    directory = new File(targetPath + packageName.replace(".", "/"));
		ArrayList<Class<?>> classes = null;
		if (directory.exists()) {
	        String[] files = directory.list();
	        if(files == null){
	        	return Collections.emptyList();
			}
			classes = new ArrayList<>();
			//noinspection ForLoopReplaceableByForEach
			for (int i = 0; i < files.length; i++) {
	            if (files[i].endsWith(".class")) {
	                
	                String className = packageName + '.' + files[i].substring(0, files[i].length() - 6);

	                try {
	                    classes.add(Class.forName(className));
	                } 
	                catch (ClassNotFoundException e) {
	                    throw new ClassNotFoundRuntimeException("ClassNotFoundException loading " + className);
	                }
	            }
	        }
	    }
	    

		if(classes == null){
			return Collections.emptyList();
		}

	    return classes;
	}
	
	
}