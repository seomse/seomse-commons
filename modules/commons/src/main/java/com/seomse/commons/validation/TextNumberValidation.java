package com.seomse.commons.validation;

import com.seomse.commons.utils.string.Check;

/**
 * 문자열 숫자로만 되어있는지 유효성 검사
 * @author macle
 */
public class TextNumberValidation implements TextValidation{
    @Override
    public boolean isValid(String text) {
        return Check.isNumber(text);
    }
}
