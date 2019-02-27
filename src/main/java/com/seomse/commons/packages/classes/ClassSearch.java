/** 
 * <pre>
 *  파 일 명 : ClassSearch.java
 *  설    명 : 클래스 검색
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassSearch {
	
	private String targetPath;
	
	/**
	 * 생성자
	 */
	public ClassSearch(){
		this.targetPath = this.getClass().getResource("/").getPath();
	}
	
	/**
	 * 생성자
	 * @param targetPath
	 */
	public ClassSearch(String targetPath){
		this.targetPath = targetPath;
	
	}

	/**
	 * 클래스패쓰얻기
	 * @return
	 */
	public String getClassPash(){
		return this.getClass().getResource("/").getPath();
	}
	
	/**
	 * 패키지내의 클래스 목록 얻기
	 * file을 사용하므로 jar로 묶여있지 않아아햠.
	 * 클래스가 .jar로 묶여있지 않고 classes등의 폴더에 
	 * @param pkgname 패키지명
	 * @return 클래스 리스트
	 */
	public List<Class<?>> getClassesForPackage(String pkgname) {

	    ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
	    
	    //패키지 절대경로
	    String classesPath = targetPath;
	
	    File directory = null;
	    directory = new File(classesPath + pkgname.replace(".", "/"));
	
	    if (directory != null && directory.exists()) {
	        String[] files = directory.list();
	        for (int i = 0; i < files.length; i++) {
	            if (files[i].endsWith(".class")) {
	                
	                String className = pkgname + '.' + files[i].substring(0, files[i].length() - 6);

	                try {
	                    classes.add(Class.forName(className));
	                } 
	                catch (ClassNotFoundException e) {
	                    throw new RuntimeException("ClassNotFoundException loading " + className);
	                }
	            }
	        }
	    }
	    

	    return classes;
	}
	
	
}