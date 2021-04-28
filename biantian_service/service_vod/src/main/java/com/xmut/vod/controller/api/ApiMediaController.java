package com.xmut.vod.controller.api;

import com.xmut.commonutils.R;
import com.xmut.vod.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/vod/media")
public class ApiMediaController {

    @Autowired
    private VideoService videoService;

    @GetMapping("get-paly-auth/{videoSourceId}")
    public R getPalyAuth(
            @PathVariable String videoSourceId
    ){
        try {
            String playAuth = videoService.getPlayAuth(videoSourceId);
            return R.ok().message("获取播放凭证成功").data("playAuth",playAuth);
        }catch (Exception e){
            return R.error().message("获取播放凭证失败");
        }
    }
}
