package com.junge.aligenie.web;

import com.junge.aligenie.entity.Device;
import com.junge.aligenie.entity.DeviceTypeOperation;
import com.junge.aligenie.repository.DeviceTypeOperationRepository;
import com.junge.aligenie.utils.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/DeviceTypeOperation")
    @ResponseBody
    public ReturnT addDeviceTypeOperation(@Valid @RequestBody DeviceTypeOperation deviceTypeOperation, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ReturnT(400,bindingResult.getFieldError().getDefaultMessage());
        }
        DeviceTypeOperation save = deviceTypeOperationRepository.save(deviceTypeOperation);
        if(save!=null){
            return new ReturnT(200,"新增成功");
        }
        return new ReturnT(500,"新增失败");
    }

    @GetMapping("/DeviceTypeOperations")
    @ResponseBody
    List<DeviceTypeOperation> getDeviceTypeOperations(String id){
        List<DeviceTypeOperation> operationList = deviceTypeOperationRepository.getDeviceTypeOperationsByDeviceType_Id(id);
        return operationList;
    }

    @DeleteMapping("/DeviceTypeOperation/{id}")
    @ResponseBody
    public ReturnT delOperations(@PathVariable("id") String id){
        deviceTypeOperationRepository.deleteById(id);
        return new ReturnT(200,"删除成功");
    }
}
