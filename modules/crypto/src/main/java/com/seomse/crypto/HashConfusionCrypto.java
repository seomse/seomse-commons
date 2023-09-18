package com.seomse.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

/**
 * @author macle
 */
public class HashConfusionCrypto {

    public static String encStr(String key, String str){
        try{
            byte [] data = str.getBytes(StandardCharsets.UTF_8);
            byte [] encData = enc(key, data);
            Base64.Encoder encoder = Base64.getEncoder();
            return new String(encoder.encode(encData));
        }catch (Exception e){
            throw new CryptoException(e);
        }
    }

    public static String decStr(String key, String encStr){
        try{
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encByte = decoder.decode(encStr);

            byte [] data = dec(key, encByte);
            return new String(data, StandardCharsets.UTF_8);
        }catch (Exception e){
            throw new CryptoException(e);
        }
    }


    public static byte [] enc(String key, byte [] data) {

        try {
            String hKey = HashConfusionString.get("MD5", key);
            byte[] keyBytes = CryptoUtils.makeKeyByte(hKey, 16);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            return  cipher.doFinal(data);
        }catch (Exception e){
            throw new CryptoException(e);
        }

    }


    public static  byte [] dec(String key, byte [] data){
        try {
            String hKey = HashConfusionString.get("MD5", key);
            byte[] keyBytes = CryptoUtils.makeKeyByte(hKey, 16);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            return cipher.doFinal(data);

        }catch (Exception e){
            throw new CryptoException(e);
        }
    }



}
