package com.xmut.msmservice.controller;


import com.xmut.commonutils.R;
import com.xmut.msmservice.service.MsmService;
import com.xmut.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送短信的方法
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){
        //先从redis获取验证码，如果取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return R.ok();
        }
        //如果redis获取不到，进行阿里云发送
        //生成随机值，传递阿里云进行发送
        code = RandomUtil.getSixBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service发送短信的冯方法
        boolean isSend = msmService.send(param,phone);
        if (isSend){
            //发送成功，把发送成功验证码放到redis里面
            //设置有效时间
            redisTemplate.opsForValue().set(phone,code,30, TimeUnit.DAYS);
            return R.ok();
        }else {
            return R.error().message("短信发送失败");
        }
    }
}
