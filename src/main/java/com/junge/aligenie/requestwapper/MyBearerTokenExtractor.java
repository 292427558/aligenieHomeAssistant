package com.junge.aligenie.requestwapper;

import com.alibaba.fastjson.JSON;
import com.junge.aligenie.bean.AliRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/7/31 13:29
 */
@Component
public class MyBearerTokenExtractor extends BearerTokenExtractor {

    private final static Log logger = LogFactory.getLog(MyBearerTokenExtractor.class);

    public MyBearerTokenExtractor() {
        super();
    }

    @Override
    public Authentication extract(HttpServletRequest request) {
        return super.extract(request);
    }

    @Override
    protected String extractToken(HttpServletRequest request) {
        // first check the header...
        String token = extractHeaderToken(request);

        // bearer type allows a request parameter as well
        if (token == null) {
            logger.debug("Token not found in headers. Trying request parameters.");
            token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
            if (token == null) {
                logger.debug("Token not found in request parameters.  Not an OAuth2 request.");
            }
            if(token==null){
                //从请求体中获取

                token = getBodyToken(request);
            }

            else {
                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, OAuth2AccessToken.BEARER_TYPE);
            }
        }

        return token;
    }

    private String getBodyToken(HttpServletRequest request) {
        String accessToken= null;

        try {
            //ServletRequest servletRequest = new BodyReaderHttpServletRequestWrapper(request);
            BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            String json = responseStrBuilder.toString();
            if(StringUtils.isEmpty(json)){
                return null;
            }
            AliRequest aliRequest = JSON.parseObject(json, AliRequest.class);
            request.setAttribute("aliRequest",aliRequest);
            accessToken = aliRequest.getPayload().getAccessToken();
            //System.out.println(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }
}
