package dev;

import com.seomse.jdbc.objects.JdbcObjects;
/**
 * 개발용 테스트 소스
 * @author macle
 */
public class InsertMain {
    public static void main(String[] args) {
        JdbcObjects.insert(new InsertObj());
    }
}
