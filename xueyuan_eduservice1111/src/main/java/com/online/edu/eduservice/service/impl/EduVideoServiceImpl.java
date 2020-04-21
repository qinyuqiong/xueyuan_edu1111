package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.mapper.EduVideoMapper;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.stereotype.Service;

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

    @Override
    public void delectVideoByCourseId(String id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }

    @Override
    public boolean removeVideo(String videoId) {
        //删除小节时，还要删除阿里云视频，后面完善
        int result = baseMapper.deleteById(videoId);
        return result>0;
    }
}
