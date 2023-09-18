package com.seomse.jdbc.example.objects;

import com.seomse.jdbc.objects.JdbcObjects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author macle
 */
public class PostgresqlFileDownloadExample {
    public static void main(String[] args) throws IOException {
        AttachFile attachFile = JdbcObjects.getObj(AttachFile.class, "file_no=2");


        File file = new File("D:\\download_test.xlsx");
        FileOutputStream fos = new FileOutputStream(file);
        //db에서 가져온 byte [] 값
        fos.write(attachFile.bytes);

    }
}
