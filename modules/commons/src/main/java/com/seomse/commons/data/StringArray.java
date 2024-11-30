package com.seomse.commons.data;

/**
 * @author macle
 */
public class StringArray extends DataArray<String>{


    public StringArray(String ...array){

        super(array);
    }

    public boolean containsArrays(String text){

        if(array == null || array.length==0){
            return false;
        }

        for(String a : array){
            if(!text.contains(a)){
                return false;
            }
        }

        return true;
    }


}
