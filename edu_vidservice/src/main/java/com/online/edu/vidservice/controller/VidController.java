package com.online.edu.vidservice.controller;

import com.online.edu.vidservice.service.VidService;
import com.online.edu.xueyuan_common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version 1.0 2020/4/25
 * @auther LENOVO
 */
@RestController
@RequestMapping("/vidservice/vod")
@CrossOrigin
public class VidController {

    @Autowired
    private VidService vidService;

    /**
     * 删除多个视频
     * @param videoList
     * @return
     */
    @DeleteMapping("removeMoreVideo")
    public R deleteMoreVideo(@RequestParam("videoList") List<String> videoList){
        vidService.deleteMoreVideo(videoList);
        return R.ok();
    }

    /**
     * 删除单个视频
     * @param videoId
     * @return
     */
    @DeleteMapping("{videoId}")
    public R delectVideoIdAliyun(@PathVariable String videoId){
        vidService.deleteAliyunVideoId(videoId);
        return R.ok();
    }

    //上传视频到服务器
    @PostMapping("upload")
    public R uploadAliyunVideo(@RequestParam("file") MultipartFile file){
        String videoId = vidService.uploadVideoAliyun(file);
        return R.ok().data("videoId",videoId);
    }
}
