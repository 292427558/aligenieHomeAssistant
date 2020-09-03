package com.junge.aligenie.config;

import com.junge.aligenie.requestwapper.MyBearerTokenExtractor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.tokenExtractor(new MyBearerTokenExtractor());
            resources.resourceId("gate").stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.requestMatchers().antMatchers("/gate/**").and()
                .authorizeRequests()
                    //配置需要认证的url，必须认证过后才可以访问
                    .antMatchers("/gate/**").authenticated();
        }
    }