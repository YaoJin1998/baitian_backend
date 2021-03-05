package com.xmut.eduservice.controller;

import com.xmut.commonutils.R;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("user")
public class LoginController {

    /*
    * 登陆功能
    * */
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    /*
    * 获取用户信息
    * */
    @GetMapping("info")
    public R info(){
        return R.ok().data("name","admin")
                .data("roles","[admin]")
                .data("avatar","https://xmut-baitian.oss-cn-beijing.aliyuncs.com/2020/09/12/76467786ca8547f7838d5f68bb8636e8file.png");
    }

    /*
    退出
    * */
    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
