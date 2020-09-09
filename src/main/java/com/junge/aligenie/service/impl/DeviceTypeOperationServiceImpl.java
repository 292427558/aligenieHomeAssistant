package com.junge.aligenie.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.junge.aligenie.bean.AliControllResult;
import com.junge.aligenie.bean.AliRequest;
import com.junge.aligenie.bean.HeaderBean;
import com.junge.aligenie.entity.DeviceTypeOperation;
import com.junge.aligenie.entity.parameter.ServiceParameter;
import com.junge.aligenie.entity.parameter.ServiceParameterrConversion;
import com.junge.aligenie.repository.DeviceTypeOperationRepository;
import com.junge.aligenie.service.AsycRestHomeAssistant;
import com.junge.aligenie.service.DeviceTypeOperationService;
import com.junge.aligenie.utils.RestHomeAssistantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/9/8 15:34
 */
@Service
public class DeviceTypeOperationServiceImpl implements DeviceTypeOperationService {

    @Autowired
    DeviceTypeOperationRepository deviceTypeOperationRepository;

    @Autowired
    AsycRestHomeAssistant asycRestHomeAssistant;

    @Override
    public DeviceTypeOperation getDeviceTypeOperation(String deviceType, String operation) {
        DeviceTypeOperation deviceTypeOperation = deviceTypeOperationRepository.getDeviceTypeOperationByDeviceType_EnglishNameAndAndOperation_Name(deviceType, operation);
        return deviceTypeOperation;
    }


    @Override
    public AliControllResult getControlResult(AliRequest aliRequest,DeviceTypeOperation deviceTypeOperation, String deviceId) {
        if(deviceTypeOperation!=null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("entity_id",deviceId);
            //请求参数
            String value = aliRequest.getPayload().getValue();
            String attribute = aliRequest.getPayload().getAttribute();
            //请求地址
            String service = deviceTypeOperation.getService();
            service.replace('.','/');
            String responseName = deviceTypeOperation.getResponseName();
            List<ServiceParameter> serviceParameters = deviceTypeOperation.getServiceParameters();
            for (ServiceParameter serviceParameter : serviceParameters) {
                String isConversion = serviceParameter.getIsConversion();
                String parameterName = serviceParameter.getParameterName();
                if("2".equals(isConversion)){
                    //需要转化参数
                    List<ServiceParameterrConversion> serviceParameterrConversions = serviceParameter.getServiceParameterrConversions();
                    if(serviceParameterrConversions!=null){
                        Map<String, ServiceParameterrConversion> conversionMap = serviceParameterrConversions.stream()
                                .collect(Collectors.toMap(ServiceParameterrConversion::getAliParameterValue, e -> e));
                        ServiceParameterrConversion serviceParameterrConversion = conversionMap.get(value);
                        if(serviceParameterrConversion!=null){
                            //转化
                            jsonObject.put(parameterName,serviceParameterrConversion.getHassParameter());
                        }
                    }
                }else {
                    //不需要转化参数-----这儿有点疑问  通常为一个参数 暂时不考虑多个参数的情况  取值为tmall中的 value
                    //String parameterName = serviceParameter.getParameterName();
                    jsonObject.put(parameterName,value);
                }
            }
            HttpEntity<String> requestEntity = RestHomeAssistantUtils.getHttpEntity(jsonObject.toString());
            //异步执行homeassistant的接口 不然会超时
            asycRestHomeAssistant.AsycExchangeRestApi("services/"+service, HttpMethod.POST,requestEntity);
            //返回响应结果
            AliControllResult aliControllResult = new AliControllResult();
            aliControllResult.setHeader(new HeaderBean("AliGenie.Iot.Device.Control",responseName,aliRequest.getHeader().getMessageId(),1));
            aliControllResult.getPayload().setDeviceId(deviceId);
            return aliControllResult;
        }
        return null;
    }
}
