package com.seomse.jdbc.example.admin;

import com.seomse.jdbc.connection.ConnectionFactory;
import com.seomse.jdbc.exception.SQLRuntimeException;
import com.seomse.jdbc.naming.JdbcMapDataHandler;
import com.seomse.jdbc.naming.JdbcNamingMap;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 테이블 데이터 복사 예제
 * 일부 컬럼 속성 변경
 * @author macle
 */
public class RowDataCopyTempTable {

    static int count = 0;

    public static void main(String[] args) {

        try{


            //템프테이블로 데이터 이관할때 속성 바꿔서이관

            String table = "t_crawling_contents";
            String copyTable ="t_crawling_contents_temp";


            String url ="";
            String user = "";
            String passwd ="";

            final int maxDataCount = 10000;

            long lastNum = 0;

            Connection select = ConnectionFactory.newConnection("maria", url, user,passwd);
            Connection insert = ConnectionFactory.newConnection("maria", url, user,passwd);


            final List<Map<String, Object>> dataList = new ArrayList<>();

            JdbcMapDataHandler handler = data -> {

                data.put(JdbcNamingMap.TABLE_NAME, copyTable);
                Long id = (Long)data.get("CONTENTS_NO");
                data.put("CONTENTS_NO", Long.toString(id));

                dataList.add(data);
                if(maxDataCount <= dataList.size()){

                    System.out.println("insert" + ++count);
                    JdbcNamingMap.insert(insert, dataList);
                    dataList.clear();
                    try{
                        if(!insert.getAutoCommit())
                            insert.commit();
                    }catch(SQLException e){
                        throw new SQLRuntimeException(e);
                    }
                }
            };


            JdbcNamingMap.receiveData(select, table,  null, null, handler);
            if(dataList.size() > 0){
                JdbcNamingMap.insert(insert, dataList);
                try{
                    if(!insert.getAutoCommit())
                        insert.commit();
                }catch(SQLException e){
                    throw new SQLRuntimeException(e);
                }
            }
            dataList.clear();
            System.out.println("complete");


        }catch(Exception e){
            e.printStackTrace();;
        }
    }
}
