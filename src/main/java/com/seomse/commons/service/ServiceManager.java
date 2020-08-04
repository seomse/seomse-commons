package com.seomse.commons.service;

import com.seomse.commons.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * <pre>
 *  파 일 명 : ServiceManager.java
 *  설    명 : 서비스 관리자
 *  작 성 자 : macle
 *  작 성 일 : 2019.11.11
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class ServiceManager {

    private static final Logger logger = LoggerFactory.getLogger(ServiceManager.class);

    private static class Singleton {
        private static final ServiceManager instance = new ServiceManager();
    }

    public static ServiceManager getInstance(){
        return Singleton.instance;
    }

    private final Map<String, Service> serviceMap = new HashMap<>();

    private final Object lock = new Object();


    /**
     * 생성자
     * 싱글턴 생성자 접근제한
     */
    private ServiceManager(){

    }

    /**
     * 서비스 추가
     * @param service service
     */
    public void addService(Service service){
        synchronized (lock){
            if(serviceMap.containsKey(service.getServiceId())){
                throw new RuntimeException("service id overlap: " + service.getServiceId());
            }
            serviceMap.put(service.getServiceId(), service);
        }
    }

    /**
     * 서비스가 중지될때 호출
     * 서비스에서 자동 호출.
     * @param serviceId
     */
    void removeService(String serviceId){
        synchronized (lock){
            serviceMap.remove(serviceId);
        }
    }

    /**
     * 서비스 중지
     * @param serviceId 서비스 아이디
     */
    public Service stopService(String serviceId){
        synchronized (lock){
            Service service = serviceMap.get(serviceId);
            if(service == null){
                return null;
            }
            service.setState(Service.State.STOP);
            return service;
        }
    }

    /**
     * 서비스 전체 중지
     */
    public void stopServiceAll(){
        synchronized (lock){
            Collection<Service> serviceColl =  serviceMap.values();
            for(Service service : serviceColl){
                service.setState(Service.State.STOP);
            }
        }
    }


    /**
     * 서비스 강제 종료
     * @param serviceId 서비스 아이디
     */
    public Service killService(String serviceId){
        synchronized (lock){


            Service service = serviceMap.get(serviceId);
            if(service == null){
                return null;
            }
            try {
                service.killService();
            }catch(Exception e){
                logger.error(ExceptionUtil.getStackTrace(e));
            }
            return service;
        }
    }

    /**
     * 서비스 전체 강제종료
     */
    public void killServiceAll(){
        synchronized (lock){
            Collection<Service> serviceColl =  serviceMap.values();
            for(Service service : serviceColl){
                try{
                    service.killService();
                }catch(Exception e){
                    logger.error(ExceptionUtil.getStackTrace(e));
                }
            }
        }
    }


    /**
     * 동작중인 서비스 아이디 배열 얻기
     * @return 동작중인 서비스 아이디 배열
     */
    public String [] getLiveServices(){
        synchronized (lock){
            List<String> serviceIdList = new ArrayList<>();
            Collection<Service> serviceColl =  serviceMap.values();
            for(Service service : serviceColl){
               if(service.isStop()){
                   continue;
               }
                serviceIdList.add(service.getServiceId());
            }


            String [] services = serviceIdList.toArray(new String [0]);

            serviceIdList.clear();
            return services;
        }
    }

}
