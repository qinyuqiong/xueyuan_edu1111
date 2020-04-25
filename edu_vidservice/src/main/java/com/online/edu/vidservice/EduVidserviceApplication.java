package com.online.edu.vidservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//若出现找不到数据库的错误就是用下面这种，
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class EduVidserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduVidserviceApplication.class, args);
    }

}
