package com.seomse.commons.crypto;

import com.seomse.crypto.CharMap;
import com.seomse.crypto.HashConfusionCrypto;

/**
 * @author macle
 */
public class HashConfusionCryptoExample {
    public static void main(String[] args) {
        String key = "test";
//
        String text = "enc dec test";

        CharMap charMap = new CharMap(CharMap.makeRandomMap());

        String encText = HashConfusionCrypto.encStr(key, text, 32, charMap);
        System.out.println(encText);


        String dec = HashConfusionCrypto.decStr(key, encText, 32, charMap);
        System.out.println(dec);

        //파일암호화
//        try {
//            File file = new File("D:\\컨텐츠\\무빙.Moving.E16~E17.1080p\\MOVING 17화.mkv");
//            byte[] data = Files.readAllBytes(file.toPath());
//
//            byte[] encData = HashConfusionCrypto.enc(key, data);
//
//
//            File encFile = new File("D:\\컨텐츠\\무빙.Moving.E16~E17.1080p\\MOVING 17화.mkv.enc");
//            try(FileOutputStream fos = new FileOutputStream(encFile)) {
//
//                fos.write(encData);
//                fos.flush();
//                fos.getFD().sync();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        //파일복호화
//        try {
//            File encFile = new File("D:\\컨텐츠\\무빙.Moving.E16~E17.1080p\\MOVING 17화.mkv.enc");
//            byte[] data = Files.readAllBytes(encFile.toPath());
//
//            byte[] decData = HashConfusionCrypto.dec(key, data);
//            File decFile = new File("D:\\컨텐츠\\무빙.Moving.E16~E17.1080p\\MOVING 17화(2).mkv");
//            try(FileOutputStream fos = new FileOutputStream(decFile)) {
//                fos.write(decData);
//                fos.flush();
//                fos.getFD().sync();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

}
