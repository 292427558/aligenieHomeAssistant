package com.junge.aligenie.web;

import com.junge.aligenie.entity.DeviceTypeOperation;
import com.junge.aligenie.repository.DeviceTypeOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/9/15 13:41
 */
@RequestMapping("/setting")
@Controller
public class DeviceTypeOperationController {

    @Autowired
    DeviceTypeOperationRepository deviceTypeOperationRepository;

    @GetMapping("/DeviceTypeOperations")
    @ResponseBody
    List<DeviceTypeOperation> getDeviceTypeOperations(String id){
        List<DeviceTypeOperation> operationList = deviceTypeOperationRepository.getDeviceTypeOperationsByDeviceType_Id(id);
        return operationList;
    }
}
