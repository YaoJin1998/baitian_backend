package com.xmut.eduservice.controller.api;

import com.xmut.commonutils.R;
import com.xmut.eduservice.model.entity.Course;
import com.xmut.eduservice.model.entity.EduSubject;
import com.xmut.eduservice.model.entity.vo.WebCourseQueryVo;
import com.xmut.eduservice.service.CourseService;
import com.xmut.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/edu/subject")
public class ApiSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @GetMapping("nest-list")
    public R nestedList(){
       List<EduSubject> subjectList = subjectService.nestedList();
       return R.ok().data("items",subjectList);
    }


}
