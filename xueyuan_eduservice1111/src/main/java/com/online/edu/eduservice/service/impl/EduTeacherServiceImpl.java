package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.query.QueryTeacher;
import com.online.edu.eduservice.mapper.EduTeacherMapper;
import com.online.edu.eduservice.service.EduCourseService;
import com.online.edu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 * 用于条件查询时导入数据
 * @author testjava
 * @since 2020-03-13
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    private EduCourseService eduCourseService;

    @Override
    public void pageListCondition(Page<EduTeacher> eduTeacherPage, QueryTeacher queryTeacher) {
        //关键：queryTeacher有传递过来的条件值，判断如果有条件值，拼接条件

        //1.判断是否有QueryTeacher的属性值
        //StringUtils.isEmpty(queryTeacher) ==》 queryTeacher == null && queryTeacher == ""
        if(StringUtils.isEmpty(queryTeacher)){
            baseMapper.selectPage(eduTeacherPage,null);
            return;
        }
        //2.如果queryTeacher不为空，从中获取条件值
        String name = queryTeacher.getName();
        String level = queryTeacher.getLevel();
        String begin = queryTeacher.getBegin();
        String end = queryTeacher.getEnd();

        //创建条件构造器
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        //如果name不为空，加入条件
        if (!StringUtils.isEmpty(level)){
            queryWrapper.like("name",name);
        }
        //同上
        if (!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }
        baseMapper.selectPage(eduTeacherPage,queryWrapper);

    }

    @Override
    public Map<String, Object> getFronTeacher(Page<EduTeacher> pageTeacher) {
        //调用方法分页查询，通过pageTeacher对象分页之后的数据
        baseMapper.selectPage(pageTeacher,null);
        //从pageTeacher分页数据获取出来，放到map集合
        List<EduTeacher> records = pageTeacher.getRecords();//分页数据
        long total = pageTeacher.getTotal();//总记录
        long size = pageTeacher.getSize();//每页显示记录数
        long pages = pageTeacher.getPages();//总页数
        long current = pageTeacher.getCurrent();//当前页
        boolean hasNext = pageTeacher.hasNext();//是否有下一页
        boolean hasPrevious = pageTeacher.hasPrevious();//是否有上一页
        //把分页数据放入map中
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("items",records);
        map.put("total",total);
        map.put("size",size);
        map.put("pages",pages);
        map.put("current",current);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        return map;
    }

    @Override
    public List<EduCourse> getCourseListByTeacherId(String id) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",1);
        List<EduCourse> list = eduCourseService.list(queryWrapper);
        return list;
    }
}
