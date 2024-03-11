package com.seomse.commons.crypto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.seomse.crypto.CharMap;
import com.seomse.crypto.HashConfusionString;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author macle
 */
public class CharMapJsonExample {

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        Gson gson =  new GsonBuilder().setPrettyPrinting().create();

        Gson gson = new Gson();

        CharMap charMap = new CharMap(CharMap.makeRandomMap());

        String s = gson.toJson(charMap.getMap(), Map.class);

        System.out.println(s);

        String hKey = HashConfusionString.get("MD5", "1", 32);
        System.out.println(hKey);
        System.out.println(hKey.length());

        String changeKey = charMap.change(hKey);
        System.out.println(changeKey);
        System.out.println(changeKey.length());

        JsonObject jsonObject = gson.fromJson(s, JsonObject.class);

        Set<String> keys =  jsonObject.keySet();

        Map<Character, Character> characterMap = new HashMap<>();

        for(String key: keys){
//            characterMap.
            characterMap.put(key.charAt(0), jsonObject.get(key).getAsString().charAt(0));

        }
        System.out.println(charMap.toString());
        System.out.println(CharMap.outMap(characterMap));

        System.out.println(charMap.toString().equals(CharMap.outMap(characterMap)));


    }

}
