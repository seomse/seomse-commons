package com.seomse.jdbc.utils;

import com.seomse.commons.utils.ExceptionUtil;
import com.seomse.commons.utils.time.Times;
import com.seomse.jdbc.JdbcQuery;
import com.seomse.jdbc.PrepareStatementData;
import com.seomse.jdbc.PrepareStatements;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 시간정보를 활용한 데이터 삭제
 * @author macle
 */
@Slf4j
public class DataDeleteUseTime {

    private final String [] tables;
    private final String timeColumn;

    public String afterTableQuery;


    public DataDeleteUseTime(String [] tables, String timeColumn){
        this.tables = tables;
        this.timeColumn = timeColumn;
    }

    public void delete(int day){
        long time = System.currentTimeMillis() - ((long)day * Times.DAY_1);
        delete(time);
    }

    private final Set<String> errorTableSet = new HashSet<>();

    public void delete(long time){

        for(String table : tables){

            try {

                Map<Integer, PrepareStatementData> prepareStatementDataMap = PrepareStatements.newTimeMap(time);
                JdbcQuery.execute("delete from " + table + " where " + timeColumn + " < ?", prepareStatementDataMap);
                if (afterTableQuery != null) {
                    JdbcQuery.execute(afterTableQuery + " " + table);
                }

                errorTableSet.remove(table);

            }catch (Exception e){
                //반복 오류로그 방지용
                if(errorTableSet.contains(table)){
                    continue;
                }
                errorTableSet.add(table);
                log.error(ExceptionUtil.getStackTrace(e));
            }
        }
    }

    public void setAfterTableQuery(String afterTableQuery) {
        this.afterTableQuery = afterTableQuery;
    }

}
