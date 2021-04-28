package com.xmut.eduservice.controller.api;

import com.xmut.commonutils.R;
import com.xmut.eduservice.model.entity.Course;
import com.xmut.eduservice.model.entity.vo.ChapterVo;
import com.xmut.eduservice.model.entity.vo.WebCourseQueryVo;
import com.xmut.eduservice.model.entity.vo.WebCourseVo;
import com.xmut.eduservice.service.ChapterService;
import com.xmut.eduservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/edu/course")
public class ApiCourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @GetMapping("list")
    private R pageList(WebCourseQueryVo webCourseQueryVo){
        List<Course> courseList =  courseService.webSelectList(webCourseQueryVo);
        return R.ok().data("courseList",courseList);
    }

    @GetMapping("get/{courseId}")
    public R getById(@PathVariable String courseId){
        WebCourseVo webCourseVo = courseService.selectWebCourseVoById(courseId);

        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);

        return R.ok().data("course",webCourseVo).data("chapterVoList",chapterVoList);

    }
}
