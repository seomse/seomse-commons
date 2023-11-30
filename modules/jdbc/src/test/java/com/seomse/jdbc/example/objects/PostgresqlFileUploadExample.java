package com.seomse.jdbc.example.objects;

import com.seomse.jdbc.objects.JdbcObjects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author macle
 */
public class PostgresqlFileUploadExample {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\test.xlsx");
        AttachFile attachFile = new AttachFile();
        attachFile.fileName = file.getName();
        attachFile.bytes = Files.readAllBytes(file.toPath());

        JdbcObjects.insert(attachFile);
    }

}
