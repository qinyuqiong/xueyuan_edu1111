package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduCourseMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.online.edu.eduservice.service.EduCourseService;
import com.online.edu.eduservice.service.EduVideoService;
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

  //课程章节
  @Autowired
  private EduChapterService eduChapterService;

  //课程小节
  @Autowired
  private EduVideoService eduVideoService;

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

  /**
   * 根据id查询课程信息
   * @param id
   * @return
   */
  @Override
  public CourseInfoForm getIdCourse(String id) {
    //根据id查询课程基础信息表
    EduCourse eduCourse = baseMapper.selectById(id);
    //判断是否取到值，取到后再进行下一步
    if (eduCourse == null){
      throw new EduException(20001,"没有课程信息");
    }

    CourseInfoForm courseInfoForm = new CourseInfoForm();
    //将取到的值转到courseInfoForm中
    BeanUtils.copyProperties(eduCourse , courseInfoForm);
    //根据id查询课程描述表
    EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
    //将课程表述取出并加到courseInfoForm中
    String description = eduCourseDescription.getDescription();
    courseInfoForm.setDescription(description);
    return courseInfoForm;
  }

  @Override
  public Boolean updateCourse(CourseInfoForm courseInfoForm) {
    EduCourse eduCourse = new EduCourse();
    BeanUtils.copyProperties(courseInfoForm,eduCourse);
    int result = baseMapper.updateById(eduCourse);
    if (result == 0){
      throw new EduException(20001,"修改分类失败");
    }
    //修改描述表
    //将取到的数据封装到eduCourseDescription中再更改
    EduCourseDescription eduCourseDescription = new EduCourseDescription();
    eduCourseDescription.setId(courseInfoForm.getId());
    eduCourseDescription.setDescription(courseInfoForm.getDescription());
    boolean b = eduCourseDescriptionService.updateById(eduCourseDescription);
    return b;
  }

  @Override
  public Boolean removeCourseId(String id) {
    //1. 先根据课程id删除课程章节
    eduChapterService.delectChapterByCourseId(id);
    //2. 再删除章节中的小节
    eduVideoService.delectVideoByCourseId(id);
    //3. 删除描述
    eduCourseDescriptionService.delectDescriptionByCourseId(id);
    //4. 删除课程
    int result = baseMapper.deleteById(id);
    return result>0;
  }
}
