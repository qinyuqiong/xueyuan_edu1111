package com.online.edu.eduservice.controller;


import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.service.EduCourseService;
import com.online.edu.xueyuan_common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-15
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 更改课程状态
     * @param courseId
     * @return
     */
    @GetMapping("publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        boolean result = eduCourseService.updateById(eduCourse);
        if (result == true){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 查询课程所有信息
     * @return
     */
    @GetMapping("getAllCourseInfo/{courseId}")
    public R getAllCourseInfo(@PathVariable String courseId){
        CourseInfoDto courseInfoDto = eduCourseService.getCourseInfoAll(courseId);
        return R.ok().data("courseInfoDto",courseInfoDto);
    }

    /**
     * 课程删除
     * @param id
     * @return
     */
    @DeleteMapping("delectCourse/{id}")
    public R delectCourse(@PathVariable String id){
        Boolean flag = eduCourseService.removeCourseId(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 修改课程信息
     * @return
     */
    @PostMapping("updateCourseInfo/{id}")
    public R updateCourseInfo(@PathVariable String id , @RequestBody CourseInfoForm courseInfoForm){
        Boolean flag = eduCourseService.updateCourse(courseInfoForm);
        if (flag == true){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 根据id查询课程信息
     * @param id
     * @return
     */
    @PostMapping("getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable String id){
        CourseInfoForm courseInfoForm = eduCourseService.getIdCourse(id);
        return R.ok().data("courseInfoForm" , courseInfoForm);
    }


    //1 添加课程信息的方法
    @PostMapping
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        String courseId = eduCourseService.insertCourseInfo(courseInfoForm);
            return R.ok().data("courseId",courseId);
    }

}

