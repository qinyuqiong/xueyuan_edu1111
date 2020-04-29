package com.online.edu.vidservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.online.edu.vidservice.service.VidService;
import com.online.edu.vidservice.utils.AliyunVodSDKUtils;
import com.online.edu.vidservice.utils.ConstantPropertiesUtil;
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
     * 根据视频id获取凭证
     * @param vid
     * @return
     */
    //根据视频id获取播放凭证
    @GetMapping("getPalyAuth/{vid}")
    public R getPlayAutoId(@PathVariable String vid) {

        try {
            //初始化客户端、请求对象和相应对象
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
            //设置请求参数
            request.setVideoId(vid);
            //获取请求响应
            response = client.getAcsResponse(request);

            //输出请求结果
            //播放凭证
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        } catch (Exception e) {
            return R.error();
        }
    }

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
