package com.online.edu.statistics.client;

import com.online.edu.xueyuan_common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @version 1.0 2020/4/26
 * @auther LENOVO
 */
@FeignClient("xueyuan-ucservice")
@Component
public interface UcenterClient {
    @GetMapping("/ucservice/ucenter-member/countRegisterNum/{day}")
    public R countRegisterNum(@PathVariable String day);
}
