package com.seomse.commons.example;

import com.seomse.commons.utils.FileUtil;
import com.seomse.commons.validation.NumberNameFileValidation;

import java.io.File;

/**
 * @author macle
 */
public class FilesExample {
    public static void main(String[] args) {

        File[] files = FileUtil.getFiles("D:\\data\\candle", new NumberNameFileValidation(), FileUtil.SORT_NAME_LONG_DESC);
        for (File file : files){
            System.out.println(file.getName());
        }

    }
}
