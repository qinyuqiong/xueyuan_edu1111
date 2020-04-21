package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-21
 */
public interface EduVideoService extends IService<EduVideo> {

    void delectVideoByCourseId(String id);

    boolean removeVideo(String videoId);
}
