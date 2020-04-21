package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.dto.EduChapterDto;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-21
 */
public interface EduChapterService extends IService<EduChapter> {

    //根据课程id删除章节
    void delectChapterByCourseId(String id);

    List<EduChapterDto> getChapterVideoListCourseId(String courseId);

    Boolean removeChapterId(String chapterId);
}
