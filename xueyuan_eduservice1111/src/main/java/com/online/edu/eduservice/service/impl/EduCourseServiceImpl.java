package com.online.edu.eduservice.service.impl;

import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduCourseMapper;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.online.edu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-15
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

  //将EduCourseDescriptionService注入
  @Autowired
  private EduCourseDescriptionService eduCourseDescriptionService;

  //添加课程信息
  @Override
  public String insertCourseInfo(CourseInfoForm courseInfoForm){
    //1 课程基本信息添加课程表
    EduCourse eduCourse = new EduCourse();
    //courseInfoForm数据复制到对象eduCourse里面，进行添加
    BeanUtils.copyProperties(courseInfoForm,eduCourse);
    int result = baseMapper.insert(eduCourse);
    //判断是否添加成功，失败抛出异常，成功则进行下一步
    if (result == 0){
      throw new EduException(20001,"添加课程信息失败");
    }

    //2 课程描述添加到课程描述表
    //创建描述表对象
    EduCourseDescription eduCourseDescription = new EduCourseDescription();
    //获取描述信息
    String description = courseInfoForm.getDescription();
    eduCourseDescription.setDescription(description);
    //获取课程id
    String courseId = eduCourse.getId();
    eduCourseDescription.setId(courseId);

    boolean save = eduCourseDescriptionService.save(eduCourseDescription);
    //判断是否成功
    if(save == true){
      return courseId;
    }else {
      return null;
    }
  }
}
