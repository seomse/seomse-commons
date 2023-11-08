package com.seomse.crypto;

import java.util.*;

/**
 * @author macle
 */
public class CharMap {
    public static final char [] CHARS = {'!','"','#','$','%','&','\'','(',')','*','+',',','-','.','/','0','1','2','3','4','5','6','7','8','9',':',';','<','=','>','?','@','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','[','\\',']','^','_','`','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','{','|','}','~'};


    public static Map<Character, Character> makeRandomMap(){
        Map<Character, Character> map = new HashMap<>();
        List<Integer> keyIndexList = new LinkedList<>();
        List<Integer> valueIndexList = new LinkedList<>();
        for (int i = 0; i < CHARS.length; i++) {
            keyIndexList.add(i);
            valueIndexList.add(i);
        }

        Random random = new Random();

        //noinspection ConditionalBreakInInfiniteLoop
        for(;;){
            if(keyIndexList.size() == 0){
                break;
            }

            char key = CHARS[keyIndexList.remove(random.nextInt(keyIndexList.size()))];
            char value = CHARS[valueIndexList.remove(random.nextInt(valueIndexList.size()))];

            map.put(key, value);
        }


        return map;
    }

    private final Map<Character, Character> map ;

    public CharMap(Map<Character, Character> map){
        this.map = map;

    }

    public char getChar(char ch){
        Character character = map.get(ch);

        if(character == null){
            return ch;
        }

        return character;
    }

    public Map<Character, Character> getMap() {
        return map;
    }

    @Override
    public String toString(){
        return viewMap(map);
    }


    public String change(String value){
        char [] chars = value.toCharArray();
        char [] newChars = new char[chars.length];
        for (int i = 0; i <chars.length ; i++) {
            newChars[i] = getChar(chars[i]);
        }

        return new String(newChars);
    }


    public static String viewMap(Map<Character, Character> map){

        if(map.size() == 0){
            return "";
        }
        Set<Character> keys = map.keySet();
        StringBuilder sb = new StringBuilder();
        for(Character key : keys){
            sb.append(", ").append(key).append("=").append(map.get(key));
        }

        return sb.substring(2);
    }


}
