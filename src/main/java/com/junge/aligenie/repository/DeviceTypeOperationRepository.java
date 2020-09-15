package com.junge.aligenie.repository;

import com.junge.aligenie.entity.DeviceTypeOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * mode Interface
 *
 * @Author LiuJun
 * @Date 2020/9/7 14:45
 */
public interface DeviceTypeOperationRepository extends JpaRepository<DeviceTypeOperation,String> {

    DeviceTypeOperation getDeviceTypeOperationByDeviceType_EnglishNameAndAndOperation_Name(String deviceType,String OperationName);

    List<DeviceTypeOperation> getDeviceTypeOperationsByDeviceType_Id(String id);
}
