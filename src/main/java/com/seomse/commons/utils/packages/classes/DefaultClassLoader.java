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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * class loader
 * @author macle
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
        int len;
        try {
            assert is != null;
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