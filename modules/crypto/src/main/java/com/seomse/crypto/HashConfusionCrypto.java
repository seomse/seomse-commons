package com.seomse.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author macle
 */
public class HashConfusionCrypto {

    public static String encStr(String key, String str){
        return encStr(key, str, 16, null);
    }

    public static String encStr(String key, String str, int keySize, CharMap charMap){
        try{
            byte [] data = str.getBytes(StandardCharsets.UTF_8);
            byte [] encData = enc(key, data, keySize, charMap);
            Base64.Encoder encoder = Base64.getEncoder();
            return new String(encoder.encode(encData));
        }catch (Exception e){
            throw new CryptoException(e);
        }
    }

    public static String decStr(String key, String str){
        return decStr(key, str, 16, null);
    }

    public static String decStr(String key, String encStr, int keySize, CharMap charMap){
        try{
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encByte = decoder.decode(encStr);

            byte [] data = dec(key, encByte, keySize, charMap);
            return new String(data, StandardCharsets.UTF_8);
        }catch (Exception e){
            throw new CryptoException(e);
        }
    }

    public static byte [] enc(String key, byte [] data){

        return enc(key, data, 16, null);
    }

    public static byte [] enc(String key, byte [] data, int keySize, CharMap charMap) {

        try {
            String hKey = HashConfusionString.get("MD5", key, keySize);

            if(charMap != null){
                hKey = charMap.change(hKey);
            }

            byte[] keyBytes = CryptoUtils.makeKeyByte(hKey, keySize);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            IvParameterSpec ivSpec;
            if(keySize == 16){
                ivSpec = new IvParameterSpec(keyBytes);
            }else{
                ivSpec = new IvParameterSpec(CryptoUtils.makeKeyByte(hKey, 16));
            }

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            return  cipher.doFinal(data);
        }catch (Exception e){
            throw new CryptoException(e);
        }

    }

    public static  byte [] dec(String key, byte [] data) {
        return dec(key, data, 16, null);
    }

    public static  byte [] dec(String key, byte [] data, int keySize, CharMap charMap){
        try {
            String hKey = HashConfusionString.get("MD5", key, keySize);

            if(charMap != null){
                hKey = charMap.change(hKey);
            }

            byte[] keyBytes = CryptoUtils.makeKeyByte(hKey, keySize);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec;
            if(keySize == 16){
                ivSpec = new IvParameterSpec(keyBytes);
            }else{
                ivSpec = new IvParameterSpec(CryptoUtils.makeKeyByte(hKey, 16));
            }
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(data);

        }catch (Exception e){
            throw new CryptoException(e);
        }
    }

}
