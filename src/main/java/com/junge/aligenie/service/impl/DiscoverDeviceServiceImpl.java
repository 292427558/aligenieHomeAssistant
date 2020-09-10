package com.junge.aligenie.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.junge.aligenie.bean.AliDiscoverResult;
import com.junge.aligenie.bean.AliRequest;
import com.junge.aligenie.bean.HeaderBean;
import com.junge.aligenie.entity.Device;
import com.junge.aligenie.repository.DeviceRepository;
import com.junge.aligenie.service.DiscoverDeviceService;
import com.junge.aligenie.utils.RestHomeAssistantUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/8/6 14:34
 */
@Slf4j
@Service
public class DiscoverDeviceServiceImpl implements DiscoverDeviceService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeviceRepository deviceRepository;

//    @Value("${HomeAssistant.url}")
//    private String url;

    @Override
    public AliDiscoverResult DiscoverDevice(AliRequest aliRequest) {
        HttpEntity<String> requestEntity = RestHomeAssistantUtils.getHttpEntity(null);
        ResponseEntity<String> response = restTemplate.exchange(RestHomeAssistantUtils.getUrl()+"states", HttpMethod.GET, requestEntity, String.class);
        String json = response.getBody();
        log.debug(json);
        //返回包含很多不必要的设备信息 在这儿去除 暂时只保留 空调和 空气净化器
        List<JSONObject> jsonArray = JSON.parseArray(json,JSONObject.class);
        AliDiscoverResult result = new AliDiscoverResult();
        String messageid ="";
        if(aliRequest!=null){
            messageid = aliRequest.getHeader().getMessageId();
        }
        HeaderBean headerBean = new HeaderBean("AliGenie.Iot.Device.Discovery", "DiscoveryDevicesResponse",messageid, 1);
        result.setHeader(headerBean);
        List<AliDiscoverResult.PayloadBean.DevicesBean> devices = result.getPayload().getDevices();
        //此处查询数据库配置的设备
        List<Device> repositoryDevices = deviceRepository.findAll();
        //将设备封装进返回对象
        copySqlDevice(repositoryDevices, devices);
        //遍历从hacs中识别的设备 需在配置中配置参数tmall_genie: true 若数据库中已经配置了 剔除
        List<String> deviceIds = getDeviceId(devices);
        for (JSONObject jsonObject : jsonArray) {
            JSONObject attributes = jsonObject.getJSONObject("attributes");
            Boolean tmall_genie = attributes.getBoolean("tmall_genie");
            if(tmall_genie!=null&&tmall_genie.equals(true)){
                String entity_id = jsonObject.getString("entity_id");
                if(deviceIds.contains(entity_id)){
                    continue;
                }
                String tmall_genie_zone = attributes.getString("tmall_genie_zone");
                String tmall_genie_name = attributes.getString("tmall_genie_name");
                String tmall_genie_type = attributes.getString("tmall_genie_type");
                String device_brand = attributes.getString("device_brand");
                String device_model = attributes.getString("device_model");
                AliDiscoverResult.PayloadBean.DevicesBean device = new AliDiscoverResult.PayloadBean.DevicesBean();
                device.setDeviceId(entity_id);
                device.setDeviceName(tmall_genie_name);
                device.setDeviceType(tmall_genie_type);
                device.setZone(tmall_genie_zone);
                //品牌和型号可为空
                device.setBrand(StringUtils.isEmpty(device_brand)?"customize":device_brand);
                device.setModel(StringUtils.isEmpty(device_model)?"customize":device_model);
                List<AliDiscoverResult.PayloadBean.DevicesBean.PropertiesBean>  propertiesBeans = new ArrayList<>();
                propertiesBeans.add(new AliDiscoverResult.PayloadBean.DevicesBean.PropertiesBean("onlinestate","online"));
                device.setProperties(propertiesBeans);
                devices.add(device);
            }

            continue;
        }
        return result;
    }

    private List<AliDiscoverResult.PayloadBean.DevicesBean> copySqlDevice(List<Device> repositoryDevices,List<AliDiscoverResult.PayloadBean.DevicesBean> devices){
        for (Device repositoryDevice : repositoryDevices) {
            AliDiscoverResult.PayloadBean.DevicesBean device = new AliDiscoverResult.PayloadBean.DevicesBean();
            device.setDeviceId(repositoryDevice.getEntityId());
            device.setDeviceName(repositoryDevice.getDeviceName());
            device.setBrand(repositoryDevice.getBrand());
            device.setModel(repositoryDevice.getModel());
            device.setDeviceType(repositoryDevice.getDeviceType());
            device.setZone(repositoryDevice.getDeviceZone());
            List<AliDiscoverResult.PayloadBean.DevicesBean.PropertiesBean>  propertiesBeans = new ArrayList<>();
            propertiesBeans.add(new AliDiscoverResult.PayloadBean.DevicesBean.PropertiesBean("onlinestate","online"));
            device.setProperties(propertiesBeans);
            devices.add(device);
        }
        return devices;
    }

    private List<String> getDeviceId(List<AliDiscoverResult.PayloadBean.DevicesBean> devices){
        List<String> collect = devices.stream().map(AliDiscoverResult.PayloadBean.DevicesBean::getDeviceId).collect(Collectors.toList());
        return collect;
    }
}
