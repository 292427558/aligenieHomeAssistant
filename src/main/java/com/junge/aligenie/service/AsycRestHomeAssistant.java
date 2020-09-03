package com.junge.aligenie.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.concurrent.Future;

/**
 * mode Interface
 *
 * @Author LiuJun
 * @Date 2020/8/6 16:10
 */
public interface AsycRestHomeAssistant {

    Future<String> AsycExchangeRestApi(String apiDomain, HttpMethod method, HttpEntity<?> requestEntity);
}
