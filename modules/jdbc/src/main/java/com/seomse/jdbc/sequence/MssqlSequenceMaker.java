package com.seomse.jdbc.sequence;

import com.seomse.jdbc.JdbcQuery;

/**
 * mssql 유형의 데이터베이스
 * @author macle
 */
public class MssqlSequenceMaker implements SequenceMaker{

    @Override
    public String nextVal(String sequenceName) {
        return JdbcQuery.getResultOne("SELECT NEXT VALUE FOR " + sequenceName);
    }

    @Override
    public long nextLong(String sequenceName) {
        return JdbcQuery.getResultLong("SELECT NEXT VALUE FOR " + sequenceName);
    }
}