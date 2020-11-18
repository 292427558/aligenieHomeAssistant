package com.junge.aligenie.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultOAuth2ExceptionRenderer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.OAuth2ExceptionRenderer;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * mode class
 *自定义认证失败的返回信息
 * @Author LiuJun
 * @Date 2020/11/18 11:33
 */

public class CustomAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
    private OAuth2ExceptionRenderer exceptionRenderer = new DefaultOAuth2ExceptionRenderer();
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(authException);
        OAuth2Exception e = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);
        if(e!=null){
            try {
                //int status = e.getHttpErrorCode();
                int status = 200;
                HttpHeaders headers = new HttpHeaders();
                headers.set("Cache-Control", "no-store");
                headers.set("Pragma", "no-cache");
                if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
                    headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
                }

                ResponseEntity<OAuth2Exception> result = new ResponseEntity<OAuth2Exception>(e, headers,
                        HttpStatus.valueOf(status));
                exceptionRenderer.handleHttpEntityResponse(result, new ServletWebRequest(request, response));
                response.flushBuffer();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else {
            super.commence(request, response, authException);
        }
    }



}
