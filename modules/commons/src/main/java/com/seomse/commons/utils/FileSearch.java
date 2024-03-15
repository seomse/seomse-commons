package com.seomse.commons.utils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 예외처리 관련 유틸
 * @author macle
 */
public class FileSearch {

    private Set<String> removeNameSet = null;

    public void addRemoveName(String fileName){
        if(removeNameSet == null){
            removeNameSet = new HashSet<>();
        }
        removeNameSet.add(fileName);
    }

    public void setRemoveNameSet(Set<String> removeNameSet) {
        this.removeNameSet = removeNameSet;
    }

    private Charset charset = StandardCharsets.UTF_8;

    public void setCharSet(String charSet) {
        this.charset = Charset.forName(charSet);
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    private Set<String> outTextSet = null;

    public void setOutTextSet(Set<String> outTextSet) {
        this.outTextSet = outTextSet;
    }

    public void addOutText(String outText){
        if(outTextSet == null){
            outTextSet = new HashSet<>();
        }
        outTextSet.add(outText);
    }

    /**
     * 
     * @param path 경로
     * @param inText 포함텍스트
     * @return intext를 포함하고 있는 모든파일 검색
     */
    public List<File> search(String path, String inText){
       String [] array = {inText};
        return search(path, array);
    }


    public List<File> search(String path, String[] inTextArray){
        List<File> allFiles = FileUtil.getFileList(path);

        List<File> searchList = new ArrayList<>();

        outer:
        for(File file: allFiles){
            if(!file.isFile()){
                continue;
            }

            if(removeNameSet != null && removeNameSet.contains(file.getName())){
                continue;
            }

            String fileText = FileUtil.getFileContents(file, charset);

            if(outTextSet != null){
                for(String outText: outTextSet) {
                    if(fileText.contains(outText)) {
                        continue outer;
                    }
                }
            }

            for(String inText : inTextArray){
                if(fileText.contains(inText)){
                    searchList.add(file);
                    break;
                }
            }

        }

        return searchList;
    }

}
