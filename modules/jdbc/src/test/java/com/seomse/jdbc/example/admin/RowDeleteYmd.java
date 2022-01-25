package com.seomse.jdbc.example.admin;

import com.seomse.commons.utils.time.YmdUtil;
import com.seomse.jdbc.JdbcQuery;
import com.seomse.jdbc.connection.ConnectionFactory;

import java.sql.Connection;
import java.util.List;
/**
 * 일별 데이터 제거 예지
 * @author macle
 */
public class RowDeleteYmd {
    public static void main(String[] args) {
        try{
            String url ="";
            String user = "";
            String passwd ="";

            Connection conn = ConnectionFactory.newConnection("maria", url, user,passwd);

            List<String> ymdList = YmdUtil.getYmdList("20180101","20201231");

            for(String ymd : ymdList){
                System.out.println(ymd);
                JdbcQuery.execute(conn, "delete from t_crawling_contents where post_ymd ='" + ymd +"'");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
