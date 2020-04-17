package com.online.edu.eduservice.entity.query;

import lombok.Data;
import org.springframework.boot.SpringApplication;

/**
 * @description:用于封装条件的实现类
 * @author: yuqiong
 * @createDate: 2020/3/14
 * @version: 1.0
 */
@Data
public class QueryTeacher {
    private String name;
    private String level;
    private String begin;//开始时间
    private String end;//结束时间
}
