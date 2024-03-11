package com.seomse.commons.utils.packages.classes;

import com.seomse.commons.data.BeginEndImpl;
import com.seomse.commons.data.NullData;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * class search
 * @author macle
 */
public class ClassUtils {


    public static boolean instanceofType(Class<?> c, Class<?> check){
        return inSet(c).contains(check);
    }

    public static Set<Class<?>> inSet(Class<?> c){
        Set<Class<?>> set = new HashSet<>();
        set.add(c);
        Collections.addAll(set, c.getInterfaces());

        for(;;){
            if(c == Object.class){
                return set;
            }

            c = c.getSuperclass();
            set.add(c);
            Collections.addAll(set, c.getInterfaces());
        }
    }


}
