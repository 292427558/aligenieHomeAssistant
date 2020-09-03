package com.junge.aligenie.service;

import com.junge.aligenie.bean.AliDiscoverResult;
import com.junge.aligenie.bean.AliRequest;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/8/6 14:33
 */

public interface DiscoverDeviceService {

    AliDiscoverResult DiscoverDevice(AliRequest aliRequest);

}
