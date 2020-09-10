package com.junge.aligenie.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.junge.aligenie.bean.*;
import com.junge.aligenie.service.AsycRestHomeAssistant;
import com.junge.aligenie.service.HomeAssistantApi;
import com.junge.aligenie.utils.RestHomeAssistantUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/7/31 16:29
 */
@Service
@Slf4j
public class HomeAssistantApiImpl implements HomeAssistantApi {

    @Autowired
    private AsycRestHomeAssistant asycRestHomeAssistant;

    /**
     * 调取homeassistant相关服务
     * @Author LiuJun
     * @Date 2020/8/4 16:11
     * @param aliRequest
     * @param deviceId
     * @param apiDomain
     * @param reponseName
     * @return com.junge.aligenie.entity.AliControllResult
     **/
    @Override
    public AliControllResult devicePowerSwitch(AliRequest aliRequest, String deviceId,String apiDomain,String reponseName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("entity_id",deviceId);
        HttpEntity<String> requestEntity = RestHomeAssistantUtils.getHttpEntity(jsonObject.toString());
        //异步执行homeassistant的接口 不然会超时
        asycRestHomeAssistant.AsycExchangeRestApi(apiDomain,HttpMethod.POST,requestEntity);
        AliControllResult aliControllResult = new AliControllResult();
        aliControllResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Control",reponseName,aliRequest.getHeader().getMessageId(),1));
        aliControllResult.getPayload().setDeviceId(deviceId);
        return aliControllResult;
    }


    /**
     * 设置空调模式
     * @Author LiuJun
     * @Date 2020/8/4 21:43
     * @param aliRequest
     * @param deviceId
     * @param apiDomain
     * @param reponseName
     * @return com.junge.aligenie.entity.AliControllResult
     **/
    @Override
    public AliControllResult deviceSetMode(AliRequest aliRequest, String deviceId,String apiDomain,String reponseName) {
        String mode = aliRequest.getPayload().getValue();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("entity_id",deviceId);
        switch (mode){
            case "cold":
                //制冷
                jsonObject.put("hvac_mode","cool");
                break;
            case "ventilate":
                //通风
                jsonObject.put("hvac_mode","fan_only");
                break;
            case "airsupply":
                //通风
                jsonObject.put("hvac_mode","fan_only");
                break;
            case "dehumidification":
                //除湿
                jsonObject.put("hvac_mode","dry");
                break;
            default:
                jsonObject.put("hvac_mode",mode);
                break;
        }
        HttpEntity<String> requestEntity = RestHomeAssistantUtils.getHttpEntity(jsonObject.toString());
        //异步执行homeassistant的接口 不然会超时
        asycRestHomeAssistant.AsycExchangeRestApi(apiDomain,HttpMethod.POST,requestEntity);
        AliControllResult aliControllResult = new AliControllResult();
        aliControllResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Control",reponseName,aliRequest.getHeader().getMessageId(),1));
        aliControllResult.getPayload().setDeviceId(deviceId);
        return aliControllResult;
    }

    @Override
    public AliControllResult setDeviceWindSpeed(AliRequest aliRequest, String deviceId,String reponseName) {
        String apiDomain = "";
        String windSpeed = aliRequest.getPayload().getValue();
        String deviceType = aliRequest.getPayload().getDeviceType();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("entity_id",deviceId);
        if("airpurifier".equals(deviceType)){
            switch (windSpeed){
                case "auto":
                    jsonObject.put("speed","Auto");
                    apiDomain = "services/fan/set_speed";
                    break;
                case "low":
                    jsonObject.put("speed","Silent");
                    apiDomain = "services/fan/set_speed";
                    break;
                case "medium":
                    jsonObject.put("level",2);
                    apiDomain = "services/xiaomi_miio/fan_set_fan_level";
                    break;
                case "high":
                    jsonObject.put("level",3);
                    apiDomain = "services/xiaomi_miio/fan_set_fan_level";
                    break;
                case "max":
                    jsonObject.put("level",3);
                    apiDomain = "services/xiaomi_miio/fan_set_fan_level";
                    break;
                case "min":
                    jsonObject.put("speed","Silent");
                    apiDomain = "services/fan/set_speed";
                    break;
                default:
                    jsonObject.put("speed","Auto");
                    apiDomain = "services/fan/set_speed";
                    break;
            }

        }
        if("aircondition".equals(deviceType)){
            switch (windSpeed){
                case "auto":
                    jsonObject.put("fan_mode","Auto");
                    break;
                case "low":
                    jsonObject.put("fan_mode","low");
                    break;
                case "medium":
                    jsonObject.put("fan_mode","medium");
                    break;
                case "high":
                    jsonObject.put("fan_mode","high");
                    break;
                case "max":
                    jsonObject.put("fan_mode","Turbo");
                    break;
                case "min":
                    jsonObject.put("fan_mode","Quiet");
                    break;
                default:
                    jsonObject.put("fan_mode","Auto");
                    break;
            }
            apiDomain = "services/climate/set_fan_mode";
        }
        HttpEntity<String> requestEntity = RestHomeAssistantUtils.getHttpEntity(jsonObject.toString());
        //异步执行homeassistant的接口 不然会超时
        asycRestHomeAssistant.AsycExchangeRestApi(apiDomain,HttpMethod.POST,requestEntity);
        AliControllResult aliControllResult = new AliControllResult();
        aliControllResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Control",reponseName,aliRequest.getHeader().getMessageId(),1));
        aliControllResult.getPayload().setDeviceId(deviceId);
        return aliControllResult;
    }

    @Override
    public AliControllResult setDeviceTemperature(AliRequest aliRequest, String deviceId,String apiDomain,String reponseName) {
        String temperature = aliRequest.getPayload().getValue();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("entity_id",deviceId);
        switch (temperature){
            case "max":
                jsonObject.put("temperature","30");
                break;
            case "min":
                jsonObject.put("temperature","16");
                break;
            default:
                int tem = Integer.parseInt(temperature);
                if(tem>30){
                    temperature="30";
                }
                if(tem<16){
                    temperature="16";
                }
                jsonObject.put("temperature",temperature);
                break;
        }
        HttpEntity<String> requestEntity = RestHomeAssistantUtils.getHttpEntity(jsonObject.toString());
        //异步执行homeassistant的接口 不然会超时
        asycRestHomeAssistant.AsycExchangeRestApi(apiDomain,HttpMethod.POST,requestEntity);
        AliControllResult aliControllResult = new AliControllResult();
        aliControllResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Control",reponseName,aliRequest.getHeader().getMessageId(),1));
        aliControllResult.getPayload().setDeviceId(deviceId);
        return aliControllResult;
    }

    @Override
    public Result queryAttributes(AliRequest aliRequest, String deviceId, List<String> attribute,String apiDomain, String reponseName) {
        HttpEntity<String> requestEntity = RestHomeAssistantUtils.getHttpEntity(null);
        ResponseEntity<String> response = restTemplate.exchange(RestHomeAssistantUtils.getUrl() +apiDomain, HttpMethod.GET, requestEntity, String.class);
        if(response.getStatusCode().equals(HttpStatus.OK)){
            String json = response.getBody();
            JSONObject jsonObject = JSON.parseObject(json);
            JSONObject attributes = jsonObject.getJSONObject("attributes");
            //组装数据返回
            AliStateResult aliStateResult = new AliStateResult();
            aliStateResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Query",reponseName,aliRequest.getHeader().getMessageId(),1));
            aliStateResult.getPayload().setDeviceId(deviceId);
            List<AliStateResult.PropertiesBean> properties = aliStateResult.getProperties();
            for (String attr : attribute) {
                String attributeRes="";
                if("pm2.5".equals(attr)){
                    attributeRes = attributes.getString("aqi");
                }else {
                    attributeRes = attributes.getString(attr);
                }
                properties.add(new AliStateResult.PropertiesBean(attr,attributeRes));
            }
            return  aliStateResult;
        }
        //返回错误消息
        AliControllResult aliControllResult = new AliControllResult();
        aliControllResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Control","ErrorResponse",aliRequest.getHeader().getMessageId(),1));
        aliControllResult.getPayload().setDeviceId(deviceId).setErrorCode("DEVICE_NOT_SUPPORT_FUNCTION").setMessage("device not support");
        return  aliControllResult;
    }

    @Override
    public Result queryAttributes(AliRequest aliRequest, String deviceId,String apiDomain, String reponseName) {
        HttpEntity<String> requestEntity = RestHomeAssistantUtils.getHttpEntity(null);
        ResponseEntity<String> response = restTemplate.exchange(RestHomeAssistantUtils.getUrl() +apiDomain, HttpMethod.GET, requestEntity, String.class);
        if(response.getStatusCode().equals(HttpStatus.OK)){
            String json = response.getBody();
            JSONObject jsonObject = JSON.parseObject(json);
            JSONObject attributes = jsonObject.getJSONObject("attributes");
            JSONObject query = attributes.getJSONObject("query");
            if(query!=null){
                //组装数据返回
                AliStateResult aliStateResult = new AliStateResult();
                aliStateResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Query",reponseName,aliRequest.getHeader().getMessageId(),1));
                aliStateResult.getPayload().setDeviceId(deviceId);
                List<AliStateResult.PropertiesBean> properties = aliStateResult.getProperties();

                //从哪个位置获取参数 state 只能获取一个 attributes 可以获取多个
                String position = query.getString("position");
                JSONObject querymap = query.getJSONObject("querymap");
                for (String key : querymap.keySet()) {
                    if("state".equals(position)){
                        //从state获取值 只能取一个
                        String aliAttr = querymap.getString(key);
                        String attributeRes = jsonObject.getString("state");
                        properties.add(new AliStateResult.PropertiesBean(aliAttr,attributeRes));
                        break;
                    }else if("attributes".equals(position)){
                        //从属性中获取
                        String aliAttr = querymap.getString(key);
                        String attributeRes = attributes.getString(key);
                        properties.add(new AliStateResult.PropertiesBean(aliAttr,attributeRes));
                    }
                }
                return  aliStateResult;
            }

        }
        //返回错误消息
        AliControllResult aliControllResult = new AliControllResult();
        aliControllResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Control","ErrorResponse",aliRequest.getHeader().getMessageId(),1));
        aliControllResult.getPayload().setDeviceId(deviceId).setErrorCode("DEVICE_NOT_SUPPORT_FUNCTION").setMessage("device not support");
        return  aliControllResult;
    }

    @Override
    public AliControllResult openSwing(AliRequest aliRequest, String swing_mode,String deviceId,String apiDomain,String reponseName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("entity_id",deviceId);
        jsonObject.put("swing_mode",swing_mode);
        HttpEntity<String> requestEntity = RestHomeAssistantUtils.getHttpEntity(jsonObject.toString());
        //异步执行homeassistant的接口 不然会超时
        asycRestHomeAssistant.AsycExchangeRestApi(apiDomain,HttpMethod.POST,requestEntity);
        AliControllResult aliControllResult = new AliControllResult();
        aliControllResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Control",reponseName,aliRequest.getHeader().getMessageId(),1));
        aliControllResult.getPayload().setDeviceId(deviceId);
        return aliControllResult;
    }

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<String> getHassEntityId() {
        ArrayList<String> list = new ArrayList<>();
        try {
            HttpEntity<String> requestEntity = RestHomeAssistantUtils.getHttpEntity(null);
            ResponseEntity<String> response = restTemplate.exchange(RestHomeAssistantUtils.getUrl() +"states", HttpMethod.GET, requestEntity, String.class);
            String json = response.getBody();
            JSONArray jsonArray = JSON.parseArray(json);

            for (Object o : jsonArray) {
                JSONObject entity = (JSONObject) o;
                String entity_id = entity.getString("entity_id");
                list.add(entity_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list.add("未能从HomeAssistant获取到实体信息，请检查配置！");
        }
        if(list.size()==0){
            list.add("未能从HomeAssistant获取到实体信息，请检查配置！");
        }
        return list;
    }
}
