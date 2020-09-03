package com.junge.aligenie.web;

import com.junge.aligenie.bean.AliRequest;
import com.junge.aligenie.bean.Result;
import com.junge.aligenie.service.DeviceControlService;
import com.junge.aligenie.service.DeviceQueryService;
import com.junge.aligenie.service.DiscoverDeviceService;
import com.junge.aligenie.service.HomeAssistantApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/7/15 16:25
 */
@RestController
@Slf4j
public class GateController {

    @Autowired
    HomeAssistantApi homeAssistantApi;

    @Autowired
    DiscoverDeviceService discoverDeviceService;

    @Autowired
    DeviceControlService deviceControlService;

    @Autowired
    DeviceQueryService deviceQueryService;

    @PostMapping("/gate")
    public Result aliGenieHandler(HttpServletRequest  request){
        long start = System.currentTimeMillis();
        log.info("======================================");
        AliRequest aliRequest = (AliRequest) request.getAttribute("aliRequest");
        log.info(aliRequest+"");
        Result result = null;
        if(aliRequest!=null){
            switch (aliRequest.getHeader().getNamespace()){
                case "AliGenie.Iot.Device.Discovery":
                    //发现设备
                    result = discoverDeviceService.DiscoverDevice(aliRequest);
                    break;
                case "AliGenie.Iot.Device.Control":
                    //设备控制
                    result = deviceControlService.deviceControl(aliRequest);
                    break;
                case "AliGenie.Iot.Device.Query":
                    //设备控制
                    result = deviceQueryService.deviceQuery(aliRequest);
                    break;
            }
        }
        long end = System.currentTimeMillis();
        log.info("请求处理耗时：{}",end-start);
        return result;
    }

}
