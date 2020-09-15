package com.junge.aligenie.web;

import com.junge.aligenie.entity.DeviceType;
import com.junge.aligenie.entity.DeviceTypeOperation;
import com.junge.aligenie.entity.parameter.ServiceParameter;
import com.junge.aligenie.repository.DeviceTypeOperationRepository;
import com.junge.aligenie.repository.ServiceParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/9/15 10:59
 */
@RequestMapping("/setting")
@Controller
public class ServiceParameterController {

    @Autowired
    ServiceParameterRepository serviceParameterRepository;

    @Autowired
    DeviceTypeOperationRepository deviceTypeOperationRepository;

    @GetMapping("/serviceParameters")
    @ResponseBody
    public List<ServiceParameter> getServiceParameters(String id){
        //List<ServiceParameter> all = serviceParameterRepository.getServiceParametersByDeviceTypeOperation_Id(id);
//        DeviceTypeOperation deviceTypeOperation = deviceTypeOperationRepository.getDeviceTypeOperationByDeviceType_Id(id);
//        List<ServiceParameter> serviceParameters = deviceTypeOperation.getServiceParameters();
        return  null;
    }

}
