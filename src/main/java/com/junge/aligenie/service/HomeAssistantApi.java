package com.junge.aligenie.service;

import com.junge.aligenie.bean.AliControllResult;
import com.junge.aligenie.bean.AliRequest;
import com.junge.aligenie.bean.AliStateResult;
import com.junge.aligenie.bean.Result;

import java.util.List;

/**
 * mode Interface
 *
 * @Author LiuJun
 * @Date 2020/7/31 16:27
 */
public interface HomeAssistantApi {

    AliControllResult devicePowerSwitch(AliRequest aliRequest, String deviceId,String apiDomain,String reponseName);

    AliControllResult deviceSetMode(AliRequest aliRequest, String deviceId,String apiDomain,String reponseName);
    AliControllResult setDeviceWindSpeed(AliRequest aliRequest, String deviceId,String reponseName);

    AliControllResult setDeviceTemperature(AliRequest aliRequest, String deviceId,String apiDomain,String reponseName);
    /**
     * 开启摆风
     * @Author LiuJun
     * @Date 2020/8/10 16:52
     * @param aliRequest
     * @param deviceId
     * @param apiDomain
     * @param reponseName
     * @return com.junge.aligenie.entity.AliControllResult
     **/
    AliControllResult openSwing(AliRequest aliRequest,String swing_mode, String deviceId,String apiDomain,String reponseName);

    /**
     * 获取homeassistant 的entityid 列表
     * @Author LiuJun
     * @Date 2020/8/13 16:43
     * @return java.util.List<java.lang.String>
     **/
    List<String> getHassEntityId();

    //查询属性 --》 Temperature --humidity
    Result queryAttributes(AliRequest aliRequest, String deviceId,List<String> attribute, String apiDomain, String reponseName);
}
