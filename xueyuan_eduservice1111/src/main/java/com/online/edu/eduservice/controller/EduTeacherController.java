package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.query.QueryTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import com.online.edu.xueyuan_common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService eduTeacherService;


    //模拟登录
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //传输数据
    @GetMapping("info")
    public R info(){
    return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn" +
            ".com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    //根据id修改讲师
    @PutMapping("{id}")
    public R updataById(@PathVariable String id,@RequestBody(required = false) EduTeacher eduTeacher){
        eduTeacher.setId(id);
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }
        return R.error();
    }


    //根据id查询讲师
    @GetMapping("getTeacherINfo/{id}")
    public R getTeacherINfo(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("eduTeacher",eduTeacher);
    }


    //添加讲师
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody(required = false) EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //多条件组合查询带分页
    @PostMapping("moreCondtionPageList/{page}/{limit}")
    //@RequestBody(required = false) 传值，fasle数据不必全写，使用此方式必须使用post请求
    public R getMoreCondtionPageList(@PathVariable Long page, @PathVariable Long limit,
                                     @RequestBody(required = false) QueryTeacher queryTeacher){
        //创建page对象，传递参数
        Page<EduTeacher> eduTeacherPage = new Page<>(page,limit);
        //调用sevice的方法实现条件查询带分页
        eduTeacherService.pageListCondition(eduTeacherPage,queryTeacher);
        //调用方法，获取分页数据
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        return R.ok().data("total",total).data("records",records);
    }

    //分页查询
    @GetMapping("pageList/{page}/{limit}")
    public R getPageTeacherList(@PathVariable Long page,@PathVariable Long limit){
        //创建page对象，传递两个参数
        Page<EduTeacher> eduTeacher = new Page<>(page,limit);
        //调用分页查询方法
        eduTeacherService.page(eduTeacher,null);
        //调用方法，获取分页数据
        long total = eduTeacher.getTotal();
        List<EduTeacher> records = eduTeacher.getRecords();
        return R.ok().data("total",total).data("records",records);
    }

    //查询所有的讲师功能
    @GetMapping
    public R getAllTeacherList(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    /**
     *  逻辑删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public boolean deleteTeacherById(@PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        return b;
    }
}

