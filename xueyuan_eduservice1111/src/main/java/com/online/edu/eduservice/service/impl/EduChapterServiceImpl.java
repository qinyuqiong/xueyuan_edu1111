package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.entity.dto.EduVideoDto;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduChapterMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-21
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;
    /**
     * 根据课程id删除小节
     * @param id
     */
    @Override
    public void delectChapterByCourseId(String id) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<EduChapterDto> getChapterVideoListCourseId(String courseId) {
        //1.根据课程id查询章节
        QueryWrapper<EduChapter> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper1);

        //2.根据课程id查询小节
        QueryWrapper<EduVideo> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(wrapper2);

        //用于存储章节和小节数据
        List<EduChapterDto> chapterDtoList = new ArrayList<>();
        //3.遍历所有的章节，复制值到dto对象里面
        for (int i = 0 ; i < eduChapters.size() ; i++){
            //获取每个章节
            EduChapter chapter = eduChapters.get(i);
            //复制值到dto对象中
            EduChapterDto eduChapterDto = new EduChapterDto();
            BeanUtils.copyProperties(chapter,eduChapterDto);
            //dto对象放到list集合中
            chapterDtoList.add(eduChapterDto);

            //创建储存小节数据集合
            List<EduVideoDto> eduVideoDtoList = new ArrayList<>();
            //4. 遍历小节
            for (int m = 0 ; m < eduVideos.size() ; m++){
                //获取每个小节
                EduVideo video = eduVideos.get(m);
                //判断小节chapter和章节id是否一样
                if (video.getChapterId().equals(chapter.getId())){
                    //转换dto对象
                    EduVideoDto eduVideoDto = new EduVideoDto();
                    BeanUtils.copyProperties(video,eduVideoDto);
                    //dto对象放到list对象
                    eduVideoDtoList.add(eduVideoDto);
                }
            }
            //把小节最终放到每个章节里面
            eduChapterDto.setChildren(eduVideoDtoList);
        }
        //返回集合
        return chapterDtoList;
    }

    /**
     * 删除章节，判断是否有小节，无则删除，有则失败
     * @param chapterId
     * @return
     */
    @Override
    public Boolean removeChapterId(String chapterId) {
        //判端是否章节里有小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(wrapper);
        if (count > 0){
            throw new EduException(20001,"删除失败");
        }
        int result = baseMapper.deleteById(chapterId);
        return result>0;
    }
}
