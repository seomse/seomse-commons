/*
 * Copyright (C) 2020 Seomse Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.seomse.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 간편한 문자열 암복호화
 * @author macle
 */
@SuppressWarnings("unused")
public class StringCrypto {


    //순서정보를 유추하지 못하게 순서위치가 일관되지 않음
    private static final char [] KEY_CHAR_ARRAY = {
            'x','y','2','Z','a','C','D','I','J','K','L','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','6','5','v','Q','R','S','T','U','V','W','X','Y','9','8','7','4','0','1','b','c','d','e','f','w','z','H','B','M','N','O','P','A','E','F','G'
    };


    private static final StringCrypto DEFAULT_INSTANCE = new StringCrypto(KEY_CHAR_ARRAY);

    /**
     * 암호화
     * @param str 암호화 시킬값
     * @return 암호화된 내용
     */
    public static String encryption(String str){
        return DEFAULT_INSTANCE.enc(str);
    }

    /**
     * 복호화
     * @param str 암호화된 문자열
     * @return 복호화된 내용
     */
    public static String decryption(String str){
        return DEFAULT_INSTANCE.dec(str);
    }

    private final char [] keyCharArray;
    private  Map<Character, Integer> lengthMap;
    private  Map<Integer, char []> charMap;

    private final int minLength;
    private final int maxLength;


    private int keyRandomLength;



    /**
     * 생성자
     * @param keyCharArray 키배열 요소
     */
    public StringCrypto(char [] keyCharArray){
        this.keyCharArray = keyCharArray;
        minLength = Math.min(keyCharArray.length, 3);
        maxLength = Math.min(keyCharArray.length, 16);
        init();
    }


    public StringCrypto(char [] keyCharArray, int min, int max){
        this.keyCharArray = keyCharArray;
        minLength = min;
        maxLength = max;
        init();
    }

    private void init(){
        lengthMap = new HashMap<>();
        charMap = new HashMap<>();

        Map<Integer, List<Character>> tempMap = new HashMap<>();

        for (int i = minLength; i <= maxLength ; i++) {
            tempMap.put(i, new ArrayList<>());
        }

        int length = minLength;

        //항상 같은 순서로 생성되어야 하므로 명확한 문법으로 활용
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i <keyCharArray.length ; i++) {
            lengthMap.put(keyCharArray[i],length );
            tempMap.get(length).add(keyCharArray[i]);

            length++;
            if(length > maxLength){
                length = minLength;
            }
        }
        for (int i = minLength; i <= maxLength ; i++) {
            List<Character> list = tempMap.get(i);
            char [] array = new char[list.size()];
            for (int j = 0; j < array.length ; j++) {
                array[j] = list.get(j);
            }
            charMap.put(i, array);
            list.clear();
        }
        tempMap.clear();

        keyRandomLength = maxLength - minLength;
    }

    /**
     * 암호화
     * @param str 암호화 시킬값
     * @return 암호화된 내용
     */
    public String enc(String str){

        try {

            Random random = new Random();

            int keyLength = random.nextInt(keyRandomLength) + minLength;

            StringBuilder keyBuilder = new StringBuilder();
            for (int i = 0; i <keyLength ; i++) {
                keyBuilder.append(keyCharArray[random.nextInt(keyCharArray.length)]);
            }

            StringBuilder result = new StringBuilder();
            char [] lengthCharArray = charMap.get(keyLength);
            result.append(lengthCharArray[random.nextInt(lengthCharArray.length)]);
            result.append(keyBuilder);
            String key = HashConfusionString.get("MD5", keyBuilder.toString());

            result.append(enc(key, str));
            return result.toString();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }




    /**
     * 복호화
     * @param str String enc value
     * @return String dec value
     */
    public String dec(String str){
        try {
            char lengthChar = str.charAt(0);
            int length = lengthMap.get(lengthChar);
            int next = length +1;
            String keyData = str.substring(1, next);
            String key = HashConfusionString.get("MD5", keyData);
            String encData = str.substring(next);
            return dec(key, encData);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 암호화
     * @param key String
     * @param value String
     * @return String encValue
     */
    public static String enc(String key, String value){
        try {
            byte[] keyBytes = makeKeyByte(key);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] results = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            Base64.Encoder encoder = Base64.getEncoder();

            return new String(encoder.encode(results));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


    /**
     * 복호화
     * @param key String
     * @param enc 암호화된 값
     * @return String dec value
     */
    public static String dec(String key, String enc){
        try {
            byte[] keyBytes= makeKeyByte(key);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            Base64.Decoder decoder = Base64.getDecoder();

            byte[] encByte = decoder.decode(enc);
            byte[] results = cipher.doFinal(encByte);

            return new String(results, StandardCharsets.UTF_8);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param key String
     * @return byte []
     */
    private static byte [] makeKeyByte(String key){
        byte[] keyBytes= new byte[16];
        byte[] b= key.getBytes(StandardCharsets.UTF_8);
        int len= b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);

        return keyBytes;
    }


    /**
     * 랜덤한 char 배열 생성
     * @return char []
     */
    public static char [] makeRandomCharArray(){

        char [] sortChars = Arrays.copyOf(KEY_CHAR_ARRAY,KEY_CHAR_ARRAY.length);

        Arrays.sort(sortChars);

        Random random = new Random();

        List<Character> list = new LinkedList<>();
        for(char ch : sortChars){
            list.add(ch);
        }

        for (int i = 0; i <sortChars.length ; i++) {
            sortChars[i] = list.remove(random.nextInt(list.size()));
        }

        return sortChars;
    }

}
