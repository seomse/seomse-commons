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
package com.seomse.jdbc.common;

import com.seomse.commons.exception.IORuntimeException;
import com.seomse.jdbc.annotation.DateTime;
import com.seomse.jdbc.annotation.FlagBoolean;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * jdbc 를 활용한 필드 세팅
 * @author macle
 */
public class JdbcField {



    /**
     *  필드를 활용한 Object 세팅
     * @param result ResultSet
     * @param field Field
     * @param columnName String
     * @param resultObj Object
     * @throws IllegalArgumentException IllegalArgumentException
     * @throws IllegalAccessException IllegalAccessException
     * @throws SQLException SQLException
     */
    @SuppressWarnings({"SizeReplaceableByIsEmpty", "RedundantCast"})
    public static void setFieldObject(ResultSet result, Field field, String columnName, Object resultObj ) throws IllegalArgumentException, IllegalAccessException, SQLException{
        field.setAccessible(true);


        if(field.getType().isEnum()){
            //noinspection unchecked,rawtypes
            field.set(resultObj, Enum.valueOf((Class<Enum>)field.getType(), result.getString(columnName)));
            return;
        }


        FlagBoolean flagBoolean = field.getAnnotation(FlagBoolean.class);

        if(flagBoolean != null && (field.getType() == Boolean.TYPE || field.getType() == Boolean.class)){
            String value = result.getString(columnName).trim();

            if(value.length() == 0){
                field.set(resultObj, false);
            }else{
                char ch = value.charAt(0);
                ch = Character.toUpperCase(ch);
                field.set(resultObj, ch == 'Y');
            }

            return;
        }


        DateTime dateTime =  field.getAnnotation(DateTime.class);

        if(dateTime == null){

            Class<?> classType  = field.getType();

            if(classType.getName().equals("[B")){
                field.set(resultObj, result.getBytes(columnName));
                return;
            }

            Object obj = result.getObject(columnName);

            if(classType == String.class){
                if(obj instanceof Clob){
                    try {

                        StringBuilder sb = new StringBuilder();
                        BufferedReader reader = new BufferedReader(((Clob) obj).getCharacterStream());
                        String dummy ;
                        while ((dummy = reader.readLine()) != null) {
                            sb.append("\n").append(dummy);
                        }
                        if(sb.length() > 0){
                            field.set(resultObj, sb.substring(1));
                        }
                    }catch (IOException e){
                        throw new IORuntimeException(e);
                    }


                }else{
                    field.set(resultObj, obj);
                }


            }else if(classType == Long.class || classType == Long.TYPE){
                try{
                    if(result.wasNull()){
                        field.set(resultObj, null);
                    }else{
                        field.set(resultObj, (long)obj);
                    }

                }catch(Exception e){
                    if(result.wasNull()){
                        field.set(resultObj, null);
                    }else{
                        field.set(resultObj, Long.parseLong(result.getString(columnName)));
                    }
                }

            }else if(classType == Integer.class || classType == Integer.TYPE){

                try{
                    if(result.wasNull()){
                        field.set(resultObj, null);
                    }else{
                        field.set(resultObj, (int)obj);
                    }

                }catch(Exception e){
                    if(result.wasNull()){
                        field.set(resultObj, null);
                    }else{
                        field.set(resultObj, Integer.parseInt(result.getString(columnName)));
                    }
                }


            }else if(classType == Float.class || classType == Float.TYPE){

                try{
                    if(result.wasNull()){
                        field.set(resultObj, null);
                    }else{
                        field.set(resultObj, (float) obj);
                    }

                }catch(Exception e){
                    if(result.wasNull()){
                        field.set(resultObj, null);
                    }else{
                        field.set(resultObj, Float.parseFloat(  result.getString(columnName)));
                    }
                }

            }else if(classType == Double.class || classType == Double.TYPE){

                try{
                    if(result.wasNull()){
                        field.set(resultObj, null);
                    }else{
                        field.set(resultObj, (double)obj);
                    }

                }catch(Exception e){
                    if(result.wasNull()){
                        field.set(resultObj, null);
                    }else{
                        field.set(resultObj, Double.parseDouble(result.getString(columnName)));
                    }
                }
            }else if(classType == Boolean.class || classType == Boolean.TYPE){

                try{
                    if(result.wasNull()){
                        field.set(resultObj, null);
                    }else{
                        field.set(resultObj, (boolean)obj);
                    }

                }catch(Exception e){
                    String value =  result.getString(columnName);
                    if(result.wasNull()){
                        field.set(resultObj, null);
                    }else{
                        field.set(resultObj, Boolean.parseBoolean(value));
                    }
                }
            }else if(classType == BigDecimal.class){

                try{
                    if(result.wasNull()){
                        field.set(resultObj, null);
                    }else{
                        field.set(resultObj, (BigDecimal)obj);
                    }

                }catch(Exception e){
                    String value =  result.getString(columnName);
                    if(result.wasNull()){
                        field.set(resultObj, null);
                    }else{
                        field.set(resultObj, new BigDecimal(value));
                    }
                }
            }
        }else{

            try{
                Timestamp timeStamp = result.getTimestamp(columnName);
                if(timeStamp == null){
                    field.set(resultObj, null);
                }else{
                    field.set(resultObj, timeStamp.getTime());
                }
            }catch(Exception e){
                Object value = result.getObject(columnName);
                if( value == null){
                    field.set(resultObj, null);
                }else{
                    //noinspection ConstantValue
                    if(value.getClass() == Long.class || value.getClass() == Long.TYPE){
                        field.set(resultObj, value);
                    }else{
                        field.set(resultObj, Long.parseLong(value.toString()));
                    }

                }


            }
        }
    }

}
