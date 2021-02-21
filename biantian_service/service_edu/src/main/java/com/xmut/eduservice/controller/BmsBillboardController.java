package com.xmut.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xmut.commonutils.R;
import com.xmut.eduservice.model.entity.BmsBillboard;
import com.xmut.eduservice.service.IBmsBillboardService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/billboard")
@CrossOrigin
public class BmsBillboardController  {

    @Resource
    private IBmsBillboardService bmsBillboardService;

    @GetMapping("/show")
    public R getNotices(){
        List<BmsBillboard> list = bmsBillboardService.list(new
                LambdaQueryWrapper<BmsBillboard>().eq(BmsBillboard::isShow,true));
        return R.ok().data("list",list.get(list.size()- 1));
    }
}
