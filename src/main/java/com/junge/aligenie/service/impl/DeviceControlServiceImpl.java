package com.junge.aligenie.service.impl;

import com.junge.aligenie.bean.AliControllResult;
import com.junge.aligenie.bean.AliRequest;
import com.junge.aligenie.bean.HeaderBean;
import com.junge.aligenie.entity.DeviceTypeOperation;
import com.junge.aligenie.service.DeviceControlService;
import com.junge.aligenie.service.DeviceTypeOperationService;
import com.junge.aligenie.service.HomeAssistantApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/8/6 14:57
 */
@Service
public class DeviceControlServiceImpl implements DeviceControlService {

    @Autowired
    HomeAssistantApi homeAssistantApi;

    @Autowired
    DeviceTypeOperationService deviceTypeOperationService;

    @Override
    public AliControllResult deviceControl(AliRequest aliRequest) {
        String namespace = aliRequest.getHeader().getNamespace();
        String name = aliRequest.getHeader().getName();
        String deviceId = aliRequest.getPayload().getDeviceId();
        String deviceType = aliRequest.getPayload().getDeviceType();
        AliControllResult aliControllResult = null;
        if("AliGenie.Iot.Device.Control".equals(namespace)){
            //此处处理数据库中配置的参数 需要有缓存 避免频繁查询数据库
            //安装设备类型查询
            DeviceTypeOperation deviceTypeOperation = deviceTypeOperationService.getDeviceTypeOperation(deviceType, name);
            aliControllResult = deviceTypeOperationService.getControlResult(aliRequest,deviceTypeOperation,deviceId);
            if(aliControllResult!=null){
                //不为空 已由数据库参数 调用homeassistant
                return aliControllResult;
            }
            //这儿为代码中内置的部分 若未从数据库查询到则走此逻辑
            switch (name){
                case "TurnOn":
                    //打开设备
                    if("aircondition".equals(deviceType)){
                        //空调
                        aliControllResult = homeAssistantApi.devicePowerSwitch(aliRequest, deviceId,"services/climate/turn_on","TurnOnResponse");
                        return aliControllResult;
                    }
                    if("airpurifier".equals(deviceType)){
                        //空气净化器
                        aliControllResult = homeAssistantApi.devicePowerSwitch(aliRequest, deviceId,"services/fan/turn_on","TurnOnResponse");
                        return aliControllResult;
                    }
                    if("switch".equals(deviceType)){
                        //开关
                        aliControllResult = homeAssistantApi.devicePowerSwitch(aliRequest, deviceId,"services/switch/turn_on","TurnOnResponse");
                        return aliControllResult;
                    }
                    if("light".equals(deviceType)){
                        //开关
                        aliControllResult = homeAssistantApi.devicePowerSwitch(aliRequest, deviceId,"services/light/turn_on","TurnOnResponse");
                        return aliControllResult;
                    }
                    break;
                case "TurnOff":
                    //关闭设备
                    if("aircondition".equals(deviceType)){
                        //空调
                        aliControllResult = homeAssistantApi.devicePowerSwitch(aliRequest, deviceId,"services/climate/turn_off","TurnOffResponse");
                        return aliControllResult;
                    }
                    if("airpurifier".equals(deviceType)){
                        //空气净化器
                        aliControllResult = homeAssistantApi.devicePowerSwitch(aliRequest, deviceId,"services/fan/turn_off","TurnOffResponse");
                        return aliControllResult;
                    }
                    if("switch".equals(deviceType)){
                        //开关
                        aliControllResult = homeAssistantApi.devicePowerSwitch(aliRequest, deviceId,"services/switch/turn_off","TurnOffResponse");
                        return aliControllResult;
                    }
                    if("light".equals(deviceType)){
                        //开关
                        aliControllResult = homeAssistantApi.devicePowerSwitch(aliRequest, deviceId,"services/light/turn_off","TurnOffResponse");
                        return aliControllResult;
                    }
                    break;
                case "SetMode":
                    //调整模式
                    if("aircondition".equals(deviceType)){
                        //空调
                        aliControllResult = homeAssistantApi.deviceSetMode(aliRequest, deviceId,"services/climate/set_hvac_mode","SetModeResponse");
                        return aliControllResult;
                    }
                    break;
                case "SetWindSpeed":
                    //调整风速
                    aliControllResult = homeAssistantApi.setDeviceWindSpeed(aliRequest, deviceId,"SetWindSpeedResponse");
                    return aliControllResult;

                case "OpenSwing":
                    //开启摆风
                    if("aircondition".equals(deviceType)){
                        //空调
                        aliControllResult = homeAssistantApi.openSwing(aliRequest,"Swing in full range", deviceId,"services/climate/set_swing_mode","OpenSwingResponse");
                        return aliControllResult;
                    }
                    break;
                case "CloseSwing":
                    //关闭摆风
                    if("aircondition".equals(deviceType)){
                        //空调
                        aliControllResult = homeAssistantApi.openSwing(aliRequest, "Default",deviceId,"services/climate/set_swing_mode","OpenSwingResponse");
                        return aliControllResult;
                    }
                    break;
                case "SetTemperature":
                    //设置温度
                    if("aircondition".equals(deviceType)){
                        //空调
                        aliControllResult = homeAssistantApi.setDeviceTemperature(aliRequest, deviceId,"services/climate/set_temperature","SetTemperatureResponse");
                        return aliControllResult;
                    }
                    break;
                default:
                    //返回错误消息
                    aliControllResult = new AliControllResult();
                    aliControllResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Control","ErrorResponse",aliRequest.getHeader().getMessageId(),1));
                    aliControllResult.getPayload().setDeviceId(deviceId).setErrorCode("DEVICE_NOT_SUPPORT_FUNCTION").setMessage("device not support");
                    return  aliControllResult;
            }
        }
        //返回错误消息
        aliControllResult = new AliControllResult();
        aliControllResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Control","ErrorResponse",aliRequest.getHeader().getMessageId(),1));
        aliControllResult.getPayload().setDeviceId(deviceId).setErrorCode("DEVICE_NOT_SUPPORT_FUNCTION").setMessage("device not support");
        return  aliControllResult;
    }
}
