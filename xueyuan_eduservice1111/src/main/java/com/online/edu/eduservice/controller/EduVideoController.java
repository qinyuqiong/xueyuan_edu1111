package com.online.edu.eduservice.controller;


import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.service.EduVideoService;
import com.online.edu.xueyuan_common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-21
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    /**
     * 添加小节
     * @param eduVideo
     * @return
     */
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        boolean save = eduVideoService.save(eduVideo);
        if (save == true){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 查询
     * @param videoId
     * @return
     */
    @GetMapping("{videoId}")
    public R getVideo(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("eduVideo",eduVideo);
    }

    /**
     * 更新
     * @param eduVideo
     * @return
     */
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        boolean result = eduVideoService.updateById(eduVideo);
        if (result == true){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 根据id删除
     * @param videoId
     * @return
     */
    @DeleteMapping("{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        boolean flag = eduVideoService.removeVideo(videoId);
        if (flag == true){
            return R.ok();
        }
        return R.error();
    }

}

