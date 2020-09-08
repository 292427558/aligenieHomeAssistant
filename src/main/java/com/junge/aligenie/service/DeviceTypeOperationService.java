package com.junge.aligenie.service;

import com.junge.aligenie.entity.DeviceTypeOperation;

/**
 * mode Interface
 *
 * @Author LiuJun
 * @Date 2020/9/8 15:31
 */
public interface DeviceTypeOperationService {

    DeviceTypeOperation getDeviceTypeOperation(String deviceType,String operation);
}
