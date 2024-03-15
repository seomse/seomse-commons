package com.seomse.commons.example;

import com.seomse.commons.utils.FileSearch;

import java.io.File;
import java.util.List;

/**
 * @author macle
 */
public class FileSearchExample {
    public static void main(String[] args) {
        FileSearch fileSearch = new FileSearch();
        fileSearch.addRemoveName("ComplaintsDataJdbc.java");
        fileSearch.addRemoveName("SequenceDataJdbc.java");
        fileSearch.addRemoveName("FileDataJdbc.java");
        fileSearch.addRemoveName("NoticeDataJdbc.java");
        fileSearch.addRemoveName("AbnormalSignsDataJdbc.java");
        fileSearch.addRemoveName("PutDataJdbc.java");

        fileSearch.addRemoveName("DataManager.java");

        fileSearch.addRemoveName("FinesfssDataDelete.java");
        fileSearch.addRemoveName("FinesfssDataSyncService.java");


        fileSearch.addOutText("@Table");
        fileSearch.addOutText("package io.runon.collect.content.finesfss.ha.dbsync;");
        List<File> fileList = fileSearch.search("C:\\project\\intellij\\runon\\content-collector\\modules\\finesfss\\src\\main", "com.seomse.jdbc");
        for(File file : fileList){
            System.out.println(file.getName());
        }

    }
}
