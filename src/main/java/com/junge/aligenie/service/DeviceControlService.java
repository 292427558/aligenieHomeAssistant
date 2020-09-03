package com.junge.aligenie.service;

import com.junge.aligenie.bean.AliControllResult;
import com.junge.aligenie.bean.AliRequest;

/**
 * mode Interface
 *
 * @Author LiuJun
 * @Date 2020/8/6 14:57
 */
public interface DeviceControlService {

    AliControllResult deviceControl(AliRequest aliRequest);

}
