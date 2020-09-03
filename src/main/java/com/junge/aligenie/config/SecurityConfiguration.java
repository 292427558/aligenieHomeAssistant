package com.junge.aligenie.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.user.name}")
    private String user;

    @Value("${spring.security.user.password}")
    private String password;



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // @formatter: off
        auth.inMemoryAuthentication()
                .withUser(user)
                .password(passwordEncoder().encode(password))
                .authorities(Collections.emptyList());
        // @formatter: on
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers()
                .frameOptions().disable()//ifream
                .and()
                .csrf().disable() //关跨域保护authorizeRequests()
                .authorizeRequests()
                .anyRequest().authenticated() //所有请求都需要通过认证
                .and()
                .formLogin();
//                .and()
//                .httpBasic(); //Basic提交
    }


//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception{
//        return super.authenticationManagerBean();
//    }
}
