package com.online.edu.eduservice.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节
 * @version 1.0 2020/4/21
 * @auther LENOVO
 */
@Data
public class EduChapterDto {

    private String id;

    private String title;

    //一章节中的所有小节
    private List<EduVideoDto> children = new ArrayList<>();

}
