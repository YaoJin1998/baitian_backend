package com.xmut.eduservice.controller.api;

import com.xmut.commonutils.R;
import com.xmut.eduservice.model.entity.Course;
import com.xmut.eduservice.model.entity.EduTeacher;
import com.xmut.eduservice.service.CourseService;
import com.xmut.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/edu/index")
public class ApiIndexController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping
    public R index(){
       List<Course> courseList =  courseService.selectHotCourse();

       List<EduTeacher> teacherList = teacherService.selectHotTeacher();

       return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
