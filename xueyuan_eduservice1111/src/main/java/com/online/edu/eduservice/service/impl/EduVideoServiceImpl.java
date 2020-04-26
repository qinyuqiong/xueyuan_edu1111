package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.client.VidClient;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.mapper.EduVideoMapper;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-21
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    //注入带调用vid的接口
    @Autowired
    private VidClient vidClient;

    /**
     * 根据课程id删除小节
     * @param id
     */
    @Override
    public void delectVideoByCourseId(String id) {
        //课程里面的所有的视频都进行删除
        //1获取课程里面的所有的视频id
        //根据课程id查询所有小节id
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",id);
        //只查询视频id字段，其他字段不查询
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapperVideo);
        //把查询出来的集合里面的视频id获取出来，构建新list集合
        List<String> videoIdList = new ArrayList<>();
        for (int i = 0 ; i < eduVideos.size() ; i++){
            //获取每个小节
            EduVideo eduVideo = eduVideos.get(i);
            //每个小节获取视频id
            String videoSourceId = eduVideo.getVideoSourceId();
            //放到list集合
            videoIdList.add(videoSourceId);
        }
        //调用方法删除视频
        vidClient.deleteMoreVideo(videoIdList);
        //删除小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }

    /**
     * 根据小节id删除小节
     * @param SectionId
     * @return
     */
    @Override
    public boolean removeVideo(String SectionId) {
        //根据小节id查询数据库获取视频id
        EduVideo eduVideo = baseMapper.selectById(SectionId);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断是否为空（null/""），不为则删除
        if (!StringUtils.isEmpty(videoSourceId)){
            //调用方法，根据id查询删除
            vidClient.delectVideoIdAliyun(videoSourceId);
        }
        //删除小节时
        int result = baseMapper.deleteById(SectionId);
        return result>0;
    }
}
