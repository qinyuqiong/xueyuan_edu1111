package com.online.edu.ek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @version 1.0 2020/4/25
 * @auther LENOVO
 */
@SpringBootApplication
@EnableEurekaServer
public class EkServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EkServerApplication.class);
    }
}
