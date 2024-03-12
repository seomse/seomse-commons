package com.seomse.commons.utils.string;
/**
 * @author macle
 */
public class BetweenString {

    private String text;

    private boolean isHtmlRemove = false;

    private boolean isNumberCharEntry = false;

    private boolean isXmlRemove = false;

    private boolean isHtmlVarRemove = false;

    public BetweenString(String text) {
        setText(text);
        setRemove(false);
    }

    public BetweenString(String text, boolean isRemove) {
        setText(text);
        setRemove(isRemove);
    }

    /**
     * 전체옵션변경
     * @param isRemove 삭제여부
     */
    public void setRemove(boolean isRemove){
        isHtmlRemove = isRemove;
        isNumberCharEntry = isRemove;
        isXmlRemove = isRemove;
        isHtmlVarRemove = isRemove;
    }


    public void setText(String text){
        this.text = text;
    }


    public void setNumberCharEntry(boolean numberCharEntry) {
        isNumberCharEntry = numberCharEntry;
    }

    public void setXmlRemove(boolean xmlRemove) {
        isXmlRemove = xmlRemove;
    }

    public void setHtmlVarRemove(boolean htmlVarRemove) {
        isHtmlVarRemove = htmlVarRemove;
    }

    /**
     * html tag remove
     * default true
     * @param htmlRemove html remove flag
     */
    public void setHtmlRemove(boolean htmlRemove) {
        isHtmlRemove = htmlRemove;
    }


    /**
     * 사이 값 얻기
     * @param begin String start with
     * @param end String end with

     * @return String
     */
    private String getValue(String begin, String end){


        int startIndex = text.indexOf(begin);
        if(startIndex == -1){
            return null;
        }

        startIndex += begin.length();
        int endIndex =  text.indexOf(end, startIndex);
        if(endIndex == -1){
            return null;
        }

        String result = getBetween(text, begin, end);

        if(isXmlRemove){
            result = TextParsing.replaceInLine(result,"<!DOCTYPE", "loose.dtd\">","");
        }

        if(isNumberCharEntry){
            result = TextParsing.replaceNumberChar(result,numberCharBegin,numberCharEnd);
        }

        if(isHtmlRemove) {
            result = Change.spaceContinue(Remove.htmlTag(result)).trim();
        }

        if(isHtmlVarRemove){
            result = TextParsing.replaceInLine(result, "var ",";","");
        }

        text = text.substring(endIndex + end.length());


        return result;
    }

    private String numberCharBegin= "&#";
    private String numberCharEnd = ";";

    public void setNumberCharBegin(String numberCharBegin) {
        this.numberCharBegin = numberCharBegin;
    }

    public void setNumberCharEnd(String numberCharEnd) {
        this.numberCharEnd = numberCharEnd;
    }

    /**
     * remove script
     * target next value
     * @param search 찾을 텍스트
     * @return remove 여부
     */
    public boolean removeNext(String search){

        int startIndex = text.indexOf(search);
        if(startIndex == -1){
            return false;
        }
        text = text.substring(startIndex + search.length()).trim();
        return true;
    }

    /**
     * original value
     * @return value
     */
    public String getText(){
        return text;
    }

    public static String getBetween(String text, String begin, String end){

        int startIndex = text.indexOf(begin);
        if(startIndex == -1){
            return null;
        }

        startIndex += begin.length();
        int endIndex =  text.indexOf(end, startIndex);
        if(endIndex == -1){
            return null;
        }

        return text.substring(startIndex, endIndex);

    }

}
