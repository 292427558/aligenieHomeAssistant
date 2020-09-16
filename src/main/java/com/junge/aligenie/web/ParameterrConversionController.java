package com.junge.aligenie.web;

import com.junge.aligenie.entity.parameter.ServiceParameter;
import com.junge.aligenie.entity.parameter.ServiceParameterrConversion;
import com.junge.aligenie.repository.ServiceParameterConversionRepository;
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
 * @Date 2020/9/16 15:51
 */
@RequestMapping("/setting")
@Controller
public class ParameterrConversionController {

    @Autowired
    ServiceParameterConversionRepository serviceParameterConversionRepository;

    @GetMapping("/parameterrConversions")
    @ResponseBody
    public List<ServiceParameterrConversion> getServiceParameters(String id){
        List<ServiceParameterrConversion> serviceParameterList = serviceParameterConversionRepository.getServiceParameterrConversionByServiceParameterId(id);
        return  serviceParameterList;
    }

    @PostMapping("/parameterrConversion")
    @ResponseBody
    public ReturnT addServiceParameter(@Valid @RequestBody ServiceParameterrConversion serviceParameterrConversion, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ReturnT(400,bindingResult.getFieldError().getDefaultMessage());
        }
        ServiceParameterrConversion save = serviceParameterConversionRepository.save(serviceParameterrConversion);
        if(save!=null){
            return new ReturnT(200,"新增成功");
        }
        return new ReturnT(500,"新增失败");
    }

    @DeleteMapping("/parameterrConversion/{id}")
    @ResponseBody
    public ReturnT delParameterrConversion(@PathVariable("id") String id){
        serviceParameterConversionRepository.deleteById(id);
        return new ReturnT(200,"删除成功");
    }
}
