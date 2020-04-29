package com.online.edu.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.entity.dto.TeacherAllInfoDto;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduCourseService;
import com.online.edu.xueyuan_common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0 2020/4/27
 * @auther LENOVO
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/frontcourse")
public class FrontEduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    /**
     * 讲师详细信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R getInfoCourseId(@PathVariable String id){
        TeacherAllInfoDto teacherAllInfo = eduCourseService.getTeacherAllInfo(id);
        List<EduChapterDto> chapterVideoListCourseId = eduChapterService.getChapterVideoListCourseId(id);
        return R.ok().data("teacherAllInfo",teacherAllInfo).data("chapterVideoListCourseId",chapterVideoListCourseId);
    }

    /**
     * 分页查询讲师
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("{page}/{limit}")
    public R getFrontCourseListPage(@PathVariable Long page , @PathVariable Long limit){
        //实现分页查询
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        //调用service实现分页查询
        Map<String,Object> map = eduCourseService.listCoursePage(pageCourse);
        return R.ok().data(map);
    }
}
