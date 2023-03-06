package com.seomse.jdbc.service;

import com.seomse.commons.service.Service;
import com.seomse.commons.utils.ExceptionUtil;
import com.seomse.commons.utils.time.Times;
import com.seomse.jdbc.JdbcQuery;
import lombok.extern.slf4j.Slf4j;

/**
 * 시퀀스 생성기
 * @author macle
 */
@Slf4j
public class VacuumService extends Service {

    private final String [] tableNames;


    public VacuumService(String [] tableNames){
        setDelayStartTime(Times.HOUR_1);
        setSleepTime(Times.HOUR_12);
        setState(State.START);
        this.tableNames = tableNames;
    }

    @Override
    public void work() {
        if(tableNames == null || tableNames.length == 0){
            log.debug("service stop " + getServiceId() + " table size 0");
            setState(State.STOP);
            return;
        }

        for(String tableName : tableNames){
            try{
                JdbcQuery.execute("vacuum " + tableName);
            }catch(Exception e){
                log.error(ExceptionUtil.getStackTrace(e));
            }
        }
    }

}
