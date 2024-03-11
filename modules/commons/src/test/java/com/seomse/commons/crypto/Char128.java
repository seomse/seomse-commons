package com.seomse.commons.crypto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * @author macle
 */
public class Char128 {
    public static void main(String[] args) {

        Set<Character> set = new HashSet<>();

        List<Character> list = new ArrayList<>();

        for (int i = 0; i <256; i++) {
            int resultchar = i;
            resultchar=resultchar%127+33;

            if(resultchar>126){
                if(resultchar <= 135){
                    resultchar= resultchar - 19;
                }else if(resultchar <= 148){
                    resultchar= resultchar - 36;
                }else{
                    resultchar= resultchar- 86;
                }
            }
            if(resultchar == 92 && i < 32){
                resultchar -= i ;
            }

            char ch = (char)resultchar;
            if(set.contains(ch)){
                continue;
            }


            list.add(ch);
            set.add(ch);
        }

        System.out.println(list.size());

        for(char ch : list){
            System.out.print(",'" + ch +"'");
        }

    }
}
