package com.seomse.commons.data;

import com.seomse.commons.utils.GsonUtils;

/**
 * @author macle
 */
public class DataArray<T> {

    T [] array;

    public DataArray(T ...array){
        this.array = array;
    }


    public T [] getArray() {
        return array;
    }

    public T get(int index){
        return array[index];
    }

    public int length(){
        return array.length;
    }

    @Override
    public String toString(){
        return GsonUtils.GSON.toJson(array);
    }

}
