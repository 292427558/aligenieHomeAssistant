package com.junge.aligenie.service.impl;

import com.junge.aligenie.entity.DeviceTypeOperation;
import com.junge.aligenie.repository.DeviceTypeOperationRepository;
import com.junge.aligenie.service.DeviceTypeOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/9/8 15:34
 */
@Service
public class DeviceTypeOperationServiceImpl implements DeviceTypeOperationService {

    @Autowired
    DeviceTypeOperationRepository deviceTypeOperationRepository;


    @Override
    public DeviceTypeOperation getDeviceTypeOperation(String deviceType, String operation) {
        DeviceTypeOperation deviceTypeOperation = deviceTypeOperationRepository.getDeviceTypeOperationByDeviceType_EnglishNameAndAndOperation_Name(deviceType, operation);
        return deviceTypeOperation;
    }
}
