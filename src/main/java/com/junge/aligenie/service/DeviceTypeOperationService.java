package com.junge.aligenie.service;

import com.junge.aligenie.bean.AliControllResult;
import com.junge.aligenie.bean.AliRequest;
import com.junge.aligenie.entity.DeviceTypeOperation;

/**
 * mode Interface
 *
 * @Author LiuJun
 * @Date 2020/9/8 15:31
 */
public interface DeviceTypeOperationService {

    /**
     * 根据设备类型和 操作 查询 homeassistant的相关参数
     * @Author LiuJun
     * @Date 2020/9/8 16:22
     * @param deviceType
     * @param operation
     * @return com.junge.aligenie.entity.DeviceTypeOperation
     **/
    DeviceTypeOperation getDeviceTypeOperation(String deviceType,String operation);

    /**
     * 根据参数返回 控制参数 若为空 则代表数据库中没有配置
     * @Author LiuJun
     * @Date 2020/9/8 16:25
     * @param deviceTypeOperation
     * @return com.junge.aligenie.bean.AliControllResult
     **/
    AliControllResult getControlResult(AliRequest aliRequest,DeviceTypeOperation deviceTypeOperation, String deviceId);

    void cacheWarmUp();
}
