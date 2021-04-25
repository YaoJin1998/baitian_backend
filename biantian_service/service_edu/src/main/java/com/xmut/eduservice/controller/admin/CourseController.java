package com.xmut.eduservice.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.commonutils.R;
import com.xmut.eduservice.model.entity.Course;
import com.xmut.eduservice.model.entity.EduTeacher;
import com.xmut.eduservice.model.entity.form.CourseInfoForm;
import com.xmut.eduservice.model.entity.vo.CoursePublishVo;
import com.xmut.eduservice.model.entity.vo.CourseQueryVo;
import com.xmut.eduservice.model.entity.vo.CourseVo;
import com.xmut.eduservice.model.entity.vo.TeacherQuery;
import com.xmut.eduservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value ="/admin/edu/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //新增课程
    @PostMapping("save-course-info")
    public R saveCourseInfo(
            @RequestBody CourseInfoForm courseInfoForm
    ){
        try {
            String courseId = courseService.saveCourseInfo(courseInfoForm);
            return R.ok().data("courseId",courseId).message("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().message("填写信息错误");
        }
    }

    @GetMapping("course-info/{id}")
    public R getById(
            @PathVariable String id
    ){
        CourseInfoForm courseInfoForm =  courseService.getCourseInfoById(id);
        if (courseInfoForm != null){
            return R.ok().data("item",courseInfoForm);
        }else {
            return R.ok().message("数据不存在");
        }

    }
    @PutMapping("update-course-info")
    public R getById(
            @RequestBody CourseInfoForm courseInfoForm
    ){
        courseService.updateCourseInfoByIf(courseInfoForm);

        return R.ok().message("修改成功");

    }

    //4.条件查询带分页的方法
    @GetMapping("list/{page}/{limit}")
    public R pageCourseVoConditon(@PathVariable long page,
                                 @PathVariable long limit,
                                  CourseQueryVo CourseQueryVo){//用@RequestBody必须用post请求，里面值要设置可以没有

        IPage<CourseVo> pageModel = courseService.selectPage(page,limit,CourseQueryVo);
        List<CourseVo> records = pageModel.getRecords();
        long total = pageModel.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

    @DeleteMapping("/remove/{id}")
    public R removeById(@PathVariable String id){

        //删除课程视频

        //删除课程封面
        courseService.removeCoverById(id);

        //删除课程
        boolean result = courseService.removeCourseById(id);
        if (result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("数据不存在");
        }
    }

    @GetMapping("course-publish/{id}")
    public R getCoursePublishVoById(
            @PathVariable String id
    ){
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVoById(id);
        if (coursePublishVo != null){
            return R.ok().data("item",coursePublishVo);
        }else {
            return R.error().message("数据不存在");
        }
    }

    @PutMapping("publish-course/{id}")
    public R publishCourseById(
            @PathVariable String id
    ){
        boolean result = courseService.publishCourseById(id);
        if (result){
            return R.ok().message("发布成功");
        }else {
            return R.error().message("数据不存在");
        }
    }

}
