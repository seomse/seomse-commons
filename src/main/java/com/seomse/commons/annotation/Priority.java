


package com.seomse.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *  파 일 명 : Priority.java
 *  설    명 : 우선순위 annotation
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.05.29
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Priority {
	int seq() default 1000;
}
