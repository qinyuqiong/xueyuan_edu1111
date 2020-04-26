package com.online.edu.ucservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EduUcserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduUcserviceApplication.class, args);
    }

}