package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.query.QueryTeacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageListCondition(Page<EduTeacher> eduTeacherPage, QueryTeacher queryTeacher);
}