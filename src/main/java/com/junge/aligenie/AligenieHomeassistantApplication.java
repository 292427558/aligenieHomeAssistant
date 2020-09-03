package com.junge.aligenie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AligenieHomeassistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(AligenieHomeassistantApplication.class, args);
    }

}
