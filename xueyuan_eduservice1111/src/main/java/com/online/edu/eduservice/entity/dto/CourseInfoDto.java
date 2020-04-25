package com.online.edu.eduservice.entity.dto;

import lombok.Data;

/**
 * 课程详细名称
 * @version 1.0 2020/4/22
 * @auther LENOVO
 */
@Data
public class CourseInfoDto {

    private String id;
    private String title;
    private String cover;
    private String price;
    private String description;
    private String teacherName;//讲师名称
    private String levelOne;//一级分类名称
    private String levelTwo;//二级分类名称



}
