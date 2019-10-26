package com.seomse.commons.config;
/**
 * <pre>
 *  파 일 명 : ConfigSet.java
 *  설    명 : 설정에서 사용되는 초기 로드정보 설정
 *             기본 설정을 변경할 경우
 *             초기 구동시 Config 호출 보다 먼저 호출되게 구현
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.05.27
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017, 2019 by ㈜섬세한사람들. All right reserved.
 */
public class ConfigSet {

    public static String CONFIG_PATH = "config/seomse_config.xml";

    public static String LOG_BACK_PATH = "config/logback.xml";

    public static int XML_FILE_PRIORITY = 1000;

    //기본 시스템 프로퍼티도 우선순위에 들어갈지 여부
    public static boolean IS_SYSTEM_PROPERTIES_USE = true;
    //기본은 제일 나중에 호출
    public static int SYSTEM_PROPERTIES_PRIORITY = Integer.MAX_VALUE;

}
