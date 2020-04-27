package com.online.edu.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import com.online.edu.xueyuan_common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 前台接口
 * @version 1.0 2020/4/27
 * @auther LENOVO
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/frontteacher")
public class FrontEduTeacherController {
    //注入service
    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 讲师详细信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R getTeacherInfoCourseId(@PathVariable String id){
        //1 根据讲师id查询讲师详细信息，返回对象
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        //2.查询讲师所有的课程，返回list集合
        List<EduCourse> courseList = eduTeacherService.getCourseListByTeacherId(id);
        return R.ok().data("eduTeacher",eduTeacher).data("courseList",courseList);
    }

    /**
     * 分页查询讲师
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("{page}/{limit}")
    public R getFrontTeacherListPage(@PathVariable Long page , @PathVariable Long limit){
        //实现分页查询
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        //调用service实现分页查询
        Map<String,Object> map = eduTeacherService.getFronTeacher(pageTeacher);
        return R.ok().data(map);
    }
}
