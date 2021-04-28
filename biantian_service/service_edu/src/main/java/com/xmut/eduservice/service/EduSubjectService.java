package com.xmut.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.eduservice.model.entity.EduSubject;
import com.xmut.eduservice.model.entity.subject.OneSubject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author YaoJin
 * @since 2020-09-13
 */


public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();

    List<EduSubject> nestedList();

}
