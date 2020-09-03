package com.junge.aligenie.web;

import com.junge.aligenie.entity.Device;
import com.junge.aligenie.entity.DeviceType;
import com.junge.aligenie.repository.DeviceRepository;
import com.junge.aligenie.repository.DeviceTypeRepository;
import com.junge.aligenie.service.HomeAssistantApi;
import com.junge.aligenie.utils.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/8/13 16:07
 */
@RequestMapping("/setting")
@Controller
public class DeviceController {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceTypeRepository deviceTypeRepository;

    @Autowired
    HomeAssistantApi homeAssistantApi;


    @PostMapping("/device")
    @ResponseBody
    public ReturnT addOperation(@Valid Device device, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ReturnT(400,bindingResult.getFieldError().getDefaultMessage());
        }
        Device save = deviceRepository.save(device);
        if(save!=null){
            return new ReturnT(200,"新增成功");
        }
        return new ReturnT(500,"新增失败");
    }

    @GetMapping("/deviceLists")
    @ResponseBody
    public Page<Device>  getDevices(Integer pageNum, Integer pageSize){
        if(pageNum==null){
            pageNum=0;
            pageSize=10;
        }
        Page<Device> all = deviceRepository.findAll(PageRequest.of(pageNum, pageSize));
        return  all;
    }

    @DeleteMapping("/device/{id}")
    @ResponseBody
    public ReturnT delOperations(@PathVariable("id") String id){
        deviceRepository.deleteById(id);
        return new ReturnT(200,"删除成功");
    }

    /**
     * homeAssistant的entityid列表
     * @Author LiuJun
     * @Date 2020/8/13 16:53
     * @return java.util.List<java.lang.String>
     **/
    @GetMapping("/entityids")
    @ResponseBody
    public List<String> getEntityids(){
        List<String> hassEntityId = homeAssistantApi.getHassEntityId();
        return  hassEntityId;
    }


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
        Collections.sort(all,(e1,e2) ->{
            //根据语音环境获取排序接口
            Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
            return com.compare(e1.getName(),e2.getName());
        });
        return  all;
    }
}
