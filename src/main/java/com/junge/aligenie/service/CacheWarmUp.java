package com.junge.aligenie.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/9/22 10:23
 */
@Component
public class CacheWarmUp implements InitializingBean {

    @Autowired
    DeviceTypeOperationService deviceTypeOperationService;

    @Override
    public void afterPropertiesSet() throws Exception {
        deviceTypeOperationService.cacheWarmUp();
    }
}
