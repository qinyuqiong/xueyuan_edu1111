package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.entity.dto.OneSubjectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-07
 */
public interface EduSubjectService extends IService<EduSubject> {


    List<String> importSubject(MultipartFile file);

    List<OneSubjectDto> getSubjectList();

    boolean deleteSubjectById(String id);

    boolean saveOneLevel(EduSubject eduSubject);

    boolean seveTwoLevel(EduSubject eduSubject);
}
