package com.junge.aligenie.service.impl;

import com.junge.aligenie.service.AsycRestHomeAssistant;
import com.junge.aligenie.utils.RestHomeAssistantUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/8/6 16:10
 */
@Service
@Slf4j
public class AsycRestHomeAssistantImpl implements AsycRestHomeAssistant {

    @Autowired
    RestTemplate restTemplate;


    /**
     * 异步执行相应接口
     * @Author LiuJun
     * @Date 2020/8/6 16:20
     * @param apiDomain
     * @param method
     * @param requestEntity
     * @return java.util.concurrent.Future<java.lang.String>
     **/
    @Override
    @Async
    public Future<String> AsycExchangeRestApi(String apiDomain, HttpMethod method, HttpEntity<?> requestEntity) {

        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(RestHomeAssistantUtils.getUrl() +apiDomain, method, requestEntity, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error("调取homeassistant接口异常,服务{}，",apiDomain,requestEntity);
        }
        String json = response.getBody();
        log.info("接口响应：{}",json);
        return new AsyncResult<>(json);
    }
}
