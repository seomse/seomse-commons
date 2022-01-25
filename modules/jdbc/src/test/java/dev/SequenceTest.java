package dev;

import com.seomse.jdbc.Database;
import com.seomse.jdbc.connection.ApplicationConnectionPool;

/**
 * 시퀀스 테스트
 * @author macle
 */
public class SequenceTest {
    public static void main(String[] args) {
        ApplicationConnectionPool.getInstance();

        System.out.println();
        System.out.println("long " + Database.nextLong("seq_ticker_change_detect"));
        System.out.println("String " + Database.nextVal("seq_ticker_change_detect"));

    }
}
