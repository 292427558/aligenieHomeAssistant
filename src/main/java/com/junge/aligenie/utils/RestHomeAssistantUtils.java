package com.junge.aligenie.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/8/6 14:37
 */
@Component
public  class RestHomeAssistantUtils {

    private static String homeToken;
    private static String url;

    public static String getHomeToken() {
        return RestHomeAssistantUtils.homeToken;
    }

    @Value("${HomeAssistant.token}")
    public void setHomeToken(String homeToken) {
        RestHomeAssistantUtils.homeToken = homeToken;
    }

    public static String getUrl() {
        return url;
    }

    @Value("${HomeAssistant.url}")
    public  void setUrl(String url) {
        RestHomeAssistantUtils.url = url;
    }

    public static HttpEntity<String> getHttpEntity(String body){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer "+homeToken);
        httpHeaders.add("Content-Type","application/json");
        HttpEntity<String> requestEntity = new HttpEntity<String>(body, httpHeaders);
        return requestEntity;
    }
}
