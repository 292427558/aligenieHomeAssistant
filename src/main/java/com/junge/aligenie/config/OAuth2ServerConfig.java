package com.junge.aligenie.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

//授权服务器配置
@Configuration
@EnableAuthorizationServer //开启授权服务
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    @Qualifier("authenticationManagerBean")
//    private AuthenticationManager authenticationManager;

//    @Autowired
////    @Qualifier("customClientDetailsService")
//    private ClientDetailsService clientDetailsService;

    @Autowired
    private UserDetailsService customUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;
    @Autowired
    private TokenStore tokenStore;



    @Bean
    public TokenStore tokenStore() {
        // return new InMemoryTokenStore();
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单提交
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter: off
        clients.inMemory()
                .withClient("tmall") //客户端唯一标识（client_id）
                .secret(passwordEncoder.encode("123456")) //客户端的密码(client_secret)，这里的密码应该是加密后的
                .authorizedGrantTypes("authorization_code","refresh_token") //授权模式标识
                .scopes("read_user_info") //作用域
                .resourceIds("gate") //资源id
//                .redirectUris("https://localhost:9090/oauth/callback"); //回调地址
                .redirectUris("https://open.bot.tmall.com/oauth/callback"); //回调地址
        // @formatter: on
    }


//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetailsService);
//    }

/*    @Bean
    @Primary
    public AuthorizationServerTokenServices tokensercice(){
        //配置token有效期
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService);
        //测试token失效期
        tokenServices.setAccessTokenValiditySeconds(60);
        tokenServices.setRefreshTokenValiditySeconds(300);
        return tokenServices;
    }*/



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
//                .authenticationManager(this.authenticationManager)
//                .tokenServices(tokensercice())
                .tokenStore(tokenStore)
                .userDetailsService(customUserDetailsService);
    }


}