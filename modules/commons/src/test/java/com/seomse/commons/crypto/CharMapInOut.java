package com.seomse.commons.crypto;

import com.seomse.crypto.CharMap;

import java.util.Map;

/**
 * @author macle
 */
public class CharMapInOut {

    public static void main(String[] args) {

        CharMap charMap = new CharMap(CharMap.makeRandomMap());
        System.out.println(charMap);


        Map<Character, Character> map2 = CharMap.makeMap(charMap.toString());


        System.out.println(CharMap.outMap(map2).equals(charMap.toString()));



    }
}
