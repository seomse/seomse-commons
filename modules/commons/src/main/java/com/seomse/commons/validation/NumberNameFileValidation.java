package com.seomse.commons.validation;

import com.seomse.commons.utils.string.Check;

import java.io.File;
/**
 * 숫자로만 이루어진 파일명 유효성 체크
 * @author macle
 */
public class NumberNameFileValidation implements FileValidation{
    @Override
    public boolean isValid(File file) {
        if(!file.isFile()){
            return false;
        }
        return Check.isNumber(file.getName());
    }
}
