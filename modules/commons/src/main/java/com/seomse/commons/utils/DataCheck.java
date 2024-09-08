package com.seomse.commons.utils;

import java.security.MessageDigest;
import java.util.List;

/**
 * @author macle
 */
public class DataCheck {

    public static boolean isEqualsObj(Object source, Object target){
        if(source == null && target == null){
            return true;
        }

        if(source == null && target != null){
            return false;
        }
        if(target == null && source != null){
            return false;
        }

        return source.equals(target);

    }

    @SuppressWarnings("rawtypes")
    public static boolean isEquals(List a, List b){
        if(a == null && b == null){
            return true;
        }

        if(a != null && b == null){
            return false;
        }

        if(a == null){
            return false;
        }

        if(a.size() != b.size()){
            return false;
        }

        for(Object value : a ){
            if(!b.contains(value)){
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isEquals(Long a, Long b){
        if(a == null && b == null){
            return true;
        }

        if(a != null && b == null){
            return false;
        }

        if(a == null){
            return false;
        }

        return a.equals(b);
    }

    public static boolean isHash(String hashAlgorithm,String hashString, byte [] bytes){
        if(hashString == null && bytes == null){
            return true;
        }

        if(hashString != null && bytes == null){
            return false;
        }

        if(hashString == null){
            return false;
        }

        try {
            MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
            StringBuilder builder = new StringBuilder();
            byte [] hashBytes = md.digest(bytes);

            for (byte b : hashBytes) {
                builder.append(String.format("%02x", b));
            }

            String byteHashStr = builder.toString();

            return byteHashStr.equals(hashString);

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
