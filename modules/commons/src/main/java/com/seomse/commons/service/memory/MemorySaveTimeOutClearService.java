package com.seomse.commons.service.memory;

import com.seomse.commons.config.Config;
import com.seomse.commons.service.Service;
import com.seomse.commons.utils.ExceptionUtil;
import com.seomse.commons.utils.time.Times;
import lombok.extern.slf4j.Slf4j;


/**
 * @author macle
 */
@Slf4j
public class MemorySaveTimeOutClearService extends Service {

    private final boolean isErrorLog ;
    public MemorySaveTimeOutClearService(){
        setState(State.START);
        setSleepTime(Config.getLong("memory.save.time.out", Times.MINUTE_5));
        isErrorLog = Config.getBoolean("memory.save.clear.error.log.flag" , true);
    }
    @SuppressWarnings("rawtypes")
    @Override
    public void work() {
        MemorySaveStorage memorySaveTimeOutStorage = MemorySaveStorage.getInstance();
        MemorySaveManager [] array = memorySaveTimeOutStorage.getArray();

        for(MemorySaveManager manager : array){
            try {
                MemorySave[] saves = manager.getArray();
                for (MemorySave save : saves) {
                    if (save.isTimeOut()) {
                        log.debug("memory clean: " + manager.getClass().getSimpleName() +", " + save.getId());

                        manager.remove(save.getId());
                        try{save.clear();}catch(Exception e){if(isErrorLog)log.error(ExceptionUtil.getStackTrace(e));}
                    }
                }
            }catch(Exception e){
                if(isErrorLog)
                    log.error(ExceptionUtil.getStackTrace(e));
            }
        }
    }


    public static void main(String[] args) {
    }
}
