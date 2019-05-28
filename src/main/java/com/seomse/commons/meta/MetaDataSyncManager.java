package com.seomse.commons.meta;

import com.seomse.commons.config.Config;
import com.seomse.commons.handler.ExceptionHandler;
import com.seomse.commons.utils.ExceptionUtil;
import com.seomse.commons.utils.PriorityUtil;
import com.seomse.commons.utils.date.RunningTime;
import com.seomse.commons.utils.date.TimeUtil;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <pre>
 *  파 일 명 : MetaDataSyncManager.java
 *  설    명 : 메타 데이터 동기화 관리자
 *             in memory 시스템을 만들기위한 관리 정보
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
public class MetaDataSyncManager {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataSyncManager.class);


    private static class Singleton{
        private static final  MetaDataSyncManager instance = new MetaDataSyncManager();
    }

    /**
     * 인스턴스 얻기
     * @return
     */
    public static MetaDataSyncManager getInstance(){
        return Singleton.instance;
    }

    private MetaDataSync[] metaDataSyncArray;

    private ExceptionHandler exceptionHandler = null;

    /**
     * 생성자
     */
    private MetaDataSyncManager(){

        try{

            String syncPackage = Config.getConfig("meta.sync.package", "com.seomse");

            Reflections ref = new Reflections (syncPackage);
            List<MetaDataSync> metaDataSyncList = new ArrayList<>();
            for (Class<?> cl : ref.getSubTypesOf(MetaDataSync.class)) {
                try{
                    MetaDataSync sync = (MetaDataSync)cl.newInstance();
                    metaDataSyncList.add(sync);

                }catch(Exception e){logger.error(ExceptionUtil.getStackTrace(e));}
            }

            if(metaDataSyncList.size() == 0){
                this.metaDataSyncArray = new MetaDataSync[0];
                return;
            }
            Comparator<MetaDataSync> syncSort = new Comparator<MetaDataSync>() {
                @Override
                public int compare(MetaDataSync i1, MetaDataSync i2 ) {
                    int seq1 = PriorityUtil.getSeq(i1.getClass());
                    int seq2 = PriorityUtil.getSeq(i2.getClass());
                    return Integer.compare(seq1, seq2);
                }
            };

            MetaDataSync[] metaDataSyncArray = metaDataSyncList.toArray( new MetaDataSync[0]);

            Arrays.sort(metaDataSyncArray, syncSort);

            this.metaDataSyncArray = metaDataSyncArray;

        }catch(Exception e){
            logger.error(ExceptionUtil.getStackTrace(e));
        }

    }

    /**
     * 초기에 처음 실행될 이벤트 정의
     */
    public void init(){
        RunningTime runningTime = new RunningTime();

        //순서정보를 명확하게 하기위해 i 사용 ( 순서가 꼭 지켜져야 함을 명시)
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < metaDataSyncArray.length ; i++) {
            try {
                MetaDataSync sync = metaDataSyncArray[i];
                logger.debug("meta sync init: " + sync.getClass().getName());
                sync.init();

                logger.debug(TimeUtil.getTimeValue(runningTime.getRunningTime()));

            }catch(Exception e){
                ExceptionUtil.exception(e,logger,exceptionHandler);
            }
        }
    }

    public void update(){
        RunningTime runningTime = new RunningTime();
        //순서정보를 명확하게 하기위해 사용 ( 순서가 꼭 지켜져야 함을 명시)
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < metaDataSyncArray.length ; i++) {
            try {
                MetaDataSync sync = metaDataSyncArray[i];
                logger.debug("meta sync update: " + sync.getClass().getName());
                sync.update();

                logger.debug(TimeUtil.getTimeValue(runningTime.getRunningTime()));

            }catch(Exception e){
                logger.error(ExceptionUtil.getStackTrace(e));
            }
        }
    }

    /**
     * 예외 핸들러 설정
     * @param exceptionHandler
     */
    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }
}
