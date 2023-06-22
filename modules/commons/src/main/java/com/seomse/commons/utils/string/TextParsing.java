package com.seomse.commons.utils.string;

/**
 * 이곳에서 새로 발견된 부분에 대한 정의는 여기서 한다.
 *
 * @author macle
 */
public class TextParsing {

    /**
     * 한라인내에서 발견된 텍스트 제거
     */
    public static String replaceInLine(String text, String beginText, String endText, String replace){

        if(text == null){
            return null;
        }
        if("".equals(text)){
            return "";
        }

        String [] lines = text.split("\n");

        StringBuilder sb = new StringBuilder();

        for(String line : lines){
            sb.append("\n");

            String changeLine = line;
            for(;;) {
                String last = changeLine;

                int beginIndex = changeLine.indexOf(beginText);
                if (beginIndex == -1) {
                    break;
                }

                int endIndex = changeLine.indexOf(endText, beginIndex + beginText.length());
                if(endIndex == -1){
                    break;
                }

                changeLine = changeLine.substring(0, beginIndex) + replace + changeLine.substring(endIndex+endText.length());
                if(last.equals(changeLine)){
                    break;
                }
            }

            sb.append(changeLine);

        }
        
        return sb.substring(1);
    }

    public static String replaceNumberChar(String text, String beginText, String endText){

        if(text == null){
            return null;
        }
        if("".equals(text)){
            return "";
        }

        String [] lines = text.split("\n");

        StringBuilder sb = new StringBuilder();

        for(String line : lines){
            sb.append("\n");

            String changeLine = line;

            int fromIndex = 0;
            for(;;) {
                String last = changeLine;

                int beginIndex = changeLine.indexOf(beginText, fromIndex);
                if (beginIndex == -1) {
                    break;
                }

                int endIndex = changeLine.indexOf(endText, beginIndex + beginText.length());
                if(endIndex == -1){
                    break;
                }

                String numberValue = changeLine.substring(beginIndex + beginText.length(), endIndex);
                if(Check.isNumber(numberValue)){

                    int chNumber = Integer.parseInt(numberValue);
                    char ch = (char)chNumber;

                    changeLine = changeLine.substring(0, beginIndex) + ch + changeLine.substring(endIndex+endText.length());
                    fromIndex = 0;
                    if(last.equals(changeLine)){
                        break;
                    }
                }else {
                    fromIndex = beginIndex + beginText.length();
                }
            }

            sb.append(changeLine);

        }

        return sb.substring(1);
    }
}
