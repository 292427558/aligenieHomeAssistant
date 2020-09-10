package com.junge.aligenie.service.impl;

import com.junge.aligenie.bean.AliControllResult;
import com.junge.aligenie.bean.AliRequest;
import com.junge.aligenie.bean.HeaderBean;
import com.junge.aligenie.bean.Result;
import com.junge.aligenie.service.DeviceQueryService;
import com.junge.aligenie.service.HomeAssistantApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/8/17 16:09
 */
@Service
public class DeviceQueryServiceImpl implements DeviceQueryService {

    @Autowired
    HomeAssistantApi homeAssistantApi;

    @Override
    public Result deviceQuery(AliRequest aliRequest) {
        String namespace = aliRequest.getHeader().getNamespace();
        String name = aliRequest.getHeader().getName();
        String deviceId = aliRequest.getPayload().getDeviceId();
        String deviceType = aliRequest.getPayload().getDeviceType();
        if("AliGenie.Iot.Device.Query".equals(namespace)) {
            switch (name) {
                case "QueryTemperature":
                    //查询温度
                    if("aircondition".equals(deviceType)){
                        //空调--貌似不支持查询当前温度 可查询设定温度
                    }else if("airpurifier".equals(deviceType)){
                        //空气净化器
                        Result result = homeAssistantApi.queryAttributes(aliRequest, deviceId, Collections.singletonList("temperature"),"states/"+deviceId, "QueryTemperatureResponse");
                        return result;
                    }
                    break;

                case "QueryHumidity":
                    //查询温度
                    if("aircondition".equals(deviceType)){
                        //空调--貌似不支持查询

                    }else if("airpurifier".equals(deviceType)){
                        //空气净化器
                        Result result = homeAssistantApi.queryAttributes(aliRequest, deviceId, Collections.singletonList("humidity"),"states/"+deviceId, "QueryHumidityResponse");
                        return result;
                    }
                    break;
                case "Query":
                    Result result = homeAssistantApi.queryAttributes(aliRequest, deviceId,"states/"+deviceId, "QueryResponse");
                    return result;
                default:
                    return null;
            }
        }
        //返回错误消息
        AliControllResult aliControllResult = new AliControllResult();
        aliControllResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Control","ErrorResponse",aliRequest.getHeader().getMessageId(),1));
        aliControllResult.getPayload().setDeviceId(deviceId).setErrorCode("DEVICE_NOT_SUPPORT_FUNCTION").setMessage("device not support");
        return  aliControllResult;
    }

}
