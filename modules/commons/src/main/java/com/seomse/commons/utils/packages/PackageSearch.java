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
package com.seomse.commons.utils.packages;

import com.seomse.commons.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 패키지 목록 검색
 * @author macle
 */
public class PackageSearch {
	
	public enum Option{
		INCLUDE //포함되어있으면
		, BEGIN //시작이
		, END //끝이
	}
	
	private final String targetPath;
	private final String startPackage;
	private final int subLength;
	
	/**
	 * 생성자
	 */
	public PackageSearch(){
		startPackage = "";
		
		this.targetPath = this.getClass().getResource("/").getPath();
		
		subLength = targetPath.length()-1;
	}
	
	/**
	 * 생성자
	 * @param targetPath String targetPath
	 * @param startPackage String startPackage
	 */
	public PackageSearch(String targetPath, String startPackage){
		this.startPackage = startPackage;
		this.targetPath = targetPath.replace(".", "/");
		if( targetPath.endsWith("/") || targetPath.endsWith("\\") ){
			subLength =  targetPath.length()-1;
		}else{
			subLength =  targetPath.length();
		}
	
	}
	
	/**
	 * 텍스트가 포함된 패키지 리스트 얻기
	 * @param text String in text
	 * @return List PackageList
	 */
	public List<String> getPackageList(String text){
		return getPackageList(text, Option.INCLUDE);
	}
	
	/**
	 * 옵션별 패키지 리스트 얻기
	 * 옵션종류
	 * 	INCLUDE //포함되어있으면
	 *	, START //시작이
	 *	, END //끝이
	 * @param text String
	 * @param option Option PackageList
	 * @return List PackageList
	 */
	public List<String> getPackageList(String text, Option option){
		List<String> packageList = new ArrayList<>();
		
		List<File> fileList = FileUtil.getFileList(targetPath + startPackage.replace(".", "/"));
		
		File pathFile = new File(targetPath);
		
		for(File file: fileList){
			if(!file.isDirectory()){
				continue;
			}
			if(file.equals(pathFile)  )
				continue;
			String fileName = file.getAbsolutePath();		
			fileName = fileName.substring(subLength);
			
			fileName = fileName.replace("/", ".");
			fileName = fileName.replace("\\", ".");
			switch(option){
			case INCLUDE:		
				if(fileName.contains(text))
					continue;
				
				break;
				
			case BEGIN:
				if(!fileName.startsWith(text))
					continue;
				break;	
				
				
			case END:
				if(!fileName.endsWith(text))
					continue;
				break;	
			}
				
			packageList.add(fileName);

		}

		return packageList;
	}
	
}