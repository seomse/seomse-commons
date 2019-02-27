/** 
 * <pre>
 *  파 일 명 : PackageSearch.java
 *  설    명 : 패키지검색
 *         
 *  작 성 자 : macle
 *  작 성 일 : 2017.09
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */

package com.seomse.commons.packages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.seomse.commons.file.FileUtil;

public class PackageSearch {
	
	public enum Option{
		INCLUDE //포함되어있으면
		, START //시작이
		, END //끝이
	}
	
	private String targetPath;
	private String startPackage;
	private int subLength;
	
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
	 * @param targetPath
	 * @param startPackage
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
	 * @param text
	 * @return
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
	 * @param text
	 * @param option
	 * @return
	 */
	public List<String> getPackageList(String text, Option option){
		List<String> packageList = new ArrayList<String>();
		
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
				
			case START:
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