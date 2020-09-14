package com.junge.aligenie.web;

import com.junge.aligenie.entity.Device;
import com.junge.aligenie.entity.DeviceType;
import com.junge.aligenie.repository.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/9/14 16:41
 */
@Controller
@RequestMapping("/setting")
public class DeviceTypeController {

    @Autowired
    DeviceTypeRepository deviceTypeRepository;

    /**
     * homeAssistant的entityid列表
     * @Author LiuJun
     * @Date 2020/8/13 16:53
     * @return java.util.List<java.lang.String>
     **/
    @GetMapping("/deviceTypes")
    @ResponseBody
    public List<DeviceType> getDeviceTypes(){
        List<DeviceType> all = deviceTypeRepository.findAll();
        Collections.sort(all,(e1, e2) ->{
            //根据语音环境获取排序接口
            Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
            return com.compare(e1.getName(),e2.getName());
        });
        return  all;
    }

    /**
     * 设备类别分页查询
     * @Author LiuJun
     * @Date 2020/9/14 16:45
     * @param pageNum
     * @param pageSize
     * @return org.springframework.data.domain.Page<com.junge.aligenie.entity.DeviceType>
     **/
    @GetMapping("/deviceTypesPage")
    @ResponseBody
    public Page<DeviceType> getDeviceTypesPage(Integer pageNum, Integer pageSize){
        if(pageNum==null){
            pageNum=0;
            pageSize=10;
        }
        Page<DeviceType> all = deviceTypeRepository.findAll(PageRequest.of(pageNum, pageSize));
//        List<DeviceType> all = deviceTypeRepository.findAll();
//        Collections.sort(all,(e1, e2) ->{
//            //根据语音环境获取排序接口
//            Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
//            return com.compare(e1.getName(),e2.getName());
//        });
        return  all;
    }

}
