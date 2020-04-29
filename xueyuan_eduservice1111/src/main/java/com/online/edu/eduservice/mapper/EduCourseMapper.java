package com.online.edu.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import com.online.edu.eduservice.entity.dto.TeacherAllInfoDto;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-04-15
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 根据课程id查询课程详情
     * @param courseId
     * @return
     */
    CourseInfoDto getCourseInfoAll(String courseId);

    //前台根据课程id查询课程详情
    TeacherAllInfoDto getTeacherAllInfo(String id);

}
