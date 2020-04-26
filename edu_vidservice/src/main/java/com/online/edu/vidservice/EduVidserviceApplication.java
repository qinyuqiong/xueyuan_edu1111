package com.online.edu.vidservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//若出现找不到数据库的错误就是用下面这种，
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class EduVidserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduVidserviceApplication.class, args);
    }

}
