package com.seomse.jdbc.naming;

import lombok.Data;

/**
 * jdbc column class
 * @author macle
 */
@Data
public class JdbcNameDataType {

    String name;

    JdbcDataType dataType = JdbcDataType.UNDEFINED;

    public JdbcNameDataType(){

    }

    public JdbcNameDataType(String name, JdbcDataType dataType){
        this.name = name;
        this.dataType = dataType;
    }

}
