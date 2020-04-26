package com.online.edu.eduservice.client;

import com.online.edu.xueyuan_common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 调用vidservice的接口
 * @version 1.0 2020/4/25
 * @auther LENOVO
 */
@FeignClient("xueyuan-vidservice")
@Component
public interface VidClient {

    /**
     * 定义调用的方法
     * 方法调用的路径
     * @param videoId
     * @return
     */
    @DeleteMapping("/vidservice/vod/{videoId}")
    public R delectVideoIdAliyun(@PathVariable("videoId") String videoId);

    @DeleteMapping("/vidservice/vod/removeMoreVideo")
    public R deleteMoreVideo(@RequestParam("videoList") List<String> videoList);
}
