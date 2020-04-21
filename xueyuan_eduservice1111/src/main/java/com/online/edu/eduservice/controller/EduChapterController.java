package com.online.edu.eduservice.controller;


import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.xueyuan_common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-21
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @DeleteMapping("{chapterId}")
    public R delectChapter(@PathVariable String chapterId){
        Boolean result = eduChapterService.removeChapterId(chapterId);
        if (result == true){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改章节
     * @param eduChapter
     * @return
     */
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        boolean result = eduChapterService.updateById(eduChapter);
        if (result == true){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改章节
     * @param chapterId
     * @return
     */
    @GetMapping("getChaptInfo/{chapterId}")
    public R getChaptInfo(@PathVariable String chapterId){
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.ok().data("eduChapter",eduChapter);
    }

    /**
     * 添加章节
     * @param eduChapter
     * @return
     */
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        boolean save = eduChapterService.save(eduChapter);
        if (save == true){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 根据id查询章节和小节
     * @param courseId
     * @return
     */
    @GetMapping("getChapterVideoList/{courseId}")
    public R getChapterVideoListCourseId(@PathVariable String courseId){
        List<EduChapterDto> list = eduChapterService.getChapterVideoListCourseId(courseId);
        return R.ok().data("items",list);
    }

}

