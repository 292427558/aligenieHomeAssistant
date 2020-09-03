package com.junge.aligenie.service;


import com.junge.aligenie.bean.AliRequest;
import com.junge.aligenie.bean.Result;

/**
 * mode Interface
 *
 * @Author LiuJun
 * @Date 2020/8/6 14:57
 */
public interface DeviceQueryService {

    //设备属性查询
    Result deviceQuery(AliRequest aliRequest);
}
