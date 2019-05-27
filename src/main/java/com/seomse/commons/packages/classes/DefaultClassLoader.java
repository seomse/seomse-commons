

package com.seomse.commons.packages.classes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 *  파 일 명 : DefaultClassLoader.java
 *  설    명 : 기본 클래스로더 (클래스 동적로딩용 클래스)
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.09
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */
public class DefaultClassLoader extends ClassLoader {
       
	
	public DefaultClassLoader(){
		super(DefaultClassLoader.class.getClassLoader()); 
	}
	
	@Override
      public Class<?> findClass(String name) {
         byte[] bt = loadClassData(name);
         return defineClass(name, bt, 0, bt.length);
      }
	
      private byte[] loadClassData(String className) {
        //read class
        InputStream is = getClass().getClassLoader().getResourceAsStream(className.replace(".", "/")+".class");
        ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
        //write into byte
        int len =0;
        try {
                     while((len=is.read())!=-1){
                           byteSt.write(len);
                      }
               } catch (IOException e) {
                     e.printStackTrace();
               }
        //convert into byte array
        return byteSt.toByteArray();
     }
    
}