package com.xmut.eduservice.controller.api;

import com.xmut.commonutils.R;
import com.xmut.eduservice.model.entity.EduTeacher;
import com.xmut.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/edu/teacher")
public class ApiTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("list")
    public R listAll(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list).message("获取讲师列表成功");
    }
}
