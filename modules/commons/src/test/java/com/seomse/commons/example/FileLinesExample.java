package com.seomse.commons.example;

import com.seomse.commons.utils.FileUtil;
import com.seomse.commons.validation.NumberNameFileValidation;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @author macle
 */
public class FileLinesExample {
    public static void main(String[] args) {
        File file = new File("D:\\data\\cryptocurrency\\futures\\candle\\BTCUSDT\\5m");

        String [] lines = FileUtil.getLines(file, StandardCharsets.UTF_8,new NumberNameFileValidation(), FileUtil.SORT_NAME_LONG,150000);
        for(String line : lines){
            System.out.println(line);
        }

        System.out.println(lines.length);
    }
}
