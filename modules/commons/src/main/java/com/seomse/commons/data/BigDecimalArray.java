package com.seomse.commons.data;

import com.google.gson.JsonArray;
import com.seomse.commons.utils.GsonUtils;

import java.math.BigDecimal;

/**
 * @author macle
 */
public class BigDecimalArray extends DataArray<BigDecimal> {

    public BigDecimalArray(BigDecimal ... array){
        super(array);
    }



    @Override
    public String toString(){
        JsonArray jsonArray = new JsonArray();
        for(BigDecimal num : array){
            jsonArray.add(num.toPlainString());
        }

        return GsonUtils.GSON.toJson(jsonArray);
    }
}
