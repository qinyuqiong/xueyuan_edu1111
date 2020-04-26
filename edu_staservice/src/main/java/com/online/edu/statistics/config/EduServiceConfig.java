package com.online.edu.statistics.config;

import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 配置类
 * @description:
 * @author: yuqiong
 * @createDate: 2020/3/13
 * @version: 1.0
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.online.edu.statistics.mapper")
public class EduServiceConfig {

    /**
     * 逻辑删除插件
     */
    @Bean
    public LogicSqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
