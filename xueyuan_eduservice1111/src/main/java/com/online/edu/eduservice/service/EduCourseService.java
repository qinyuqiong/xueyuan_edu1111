package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import com.online.edu.eduservice.entity.dto.TeacherAllInfoDto;
import com.online.edu.eduservice.entity.form.CourseInfoForm;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-15
 */
public interface EduCourseService extends IService<EduCourse> {

    String insertCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getIdCourse(String id);

    Boolean updateCourse(CourseInfoForm courseInfoForm);

    Boolean removeCourseId(String id);

    CourseInfoDto getCourseInfoAll(String courseId);

    Map<String, Object> listCoursePage(Page<EduCourse> pageCourse);

    TeacherAllInfoDto getTeacherAllInfo(String id);
}
