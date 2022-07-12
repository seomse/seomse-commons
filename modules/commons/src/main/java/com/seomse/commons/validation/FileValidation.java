package com.seomse.commons.validation;

import java.io.File;

/**
 * 파일 유효성 체크
 * @author macle
 */
public interface FileValidation {
    boolean isValid(File file);
}
