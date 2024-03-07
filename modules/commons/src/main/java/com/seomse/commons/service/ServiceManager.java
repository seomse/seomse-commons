/*
 * Copyright (C) 2020 Seomse Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.seomse.commons.service;

import com.seomse.commons.exception.OverlapException;
import com.seomse.commons.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 서비스 관리
 * 전체 시작 종료, 추가 삭제 등의 이벤트 지원
 * 싱글턴
 * @author macle
 */
@Slf4j
public class ServiceManager {

    private static class Singleton {
        private static final ServiceManager instance = new ServiceManager();
    }

    public static ServiceManager getInstance(){
        return Singleton.instance;
    }

    private final Map<String, Service> serviceMap = new HashMap<>();

    private final Object lock = new Object();


    /**
     * Singleton
     */
    private ServiceManager(){

    }

    public void addService(Service service){
        synchronized (lock){
            if(serviceMap.containsKey(service.getServiceId())){
                throw new OverlapException("service id overlap: " + service.getServiceId());
            }
            serviceMap.put(service.getServiceId(), service);
        }
    }

    void removeService(String serviceId){
        synchronized (lock){
            serviceMap.remove(serviceId);
        }
    }

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

    public void stopServiceAll(){
        synchronized (lock){
            Collection<Service> serviceColl =  serviceMap.values();
            for(Service service : serviceColl){
                service.setState(Service.State.STOP);
            }
        }
    }


    public Service killService(String serviceId){
        synchronized (lock){


            Service service = serviceMap.get(serviceId);
            if(service == null){
                return null;
            }
            try {
                service.killService();
            }catch(Exception e){
                log.error(ExceptionUtil.getStackTrace(e));
            }
            return service;
        }
    }

    public void killServiceAll(){
        synchronized (lock){
            Collection<Service> serviceColl =  serviceMap.values();
            for(Service service : serviceColl){
                try{
                    service.killService();
                }catch(Exception e){
                    log.error(ExceptionUtil.getStackTrace(e));
                }
            }
        }
    }


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
