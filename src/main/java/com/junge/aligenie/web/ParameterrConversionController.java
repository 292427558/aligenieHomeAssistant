package com.junge.aligenie.web;

import com.junge.aligenie.repository.ServiceParameterConversionRepository;
import com.junge.aligenie.utils.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @DeleteMapping("/parameterrConversion/{id}")
    @ResponseBody
    public ReturnT delParameterrConversion(@PathVariable("id") String id){
        serviceParameterConversionRepository.deleteById(id);
        return new ReturnT(200,"删除成功");
    }
}
