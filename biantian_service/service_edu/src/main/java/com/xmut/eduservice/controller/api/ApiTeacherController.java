package com.xmut.eduservice.controller.api;

import com.xmut.commonutils.R;
import com.xmut.eduservice.model.entity.EduTeacher;
import com.xmut.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("get/{id}")
    public R get(
            @PathVariable String id
    ){
        Map<String,Object> map = teacherService.selectTeacherInfoById(id);
        return R.ok().data(map);
    }
}
