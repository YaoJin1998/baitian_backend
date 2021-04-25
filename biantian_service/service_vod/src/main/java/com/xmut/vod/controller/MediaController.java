package com.xmut.vod.controller;


import com.xmut.commonutils.R;
import com.xmut.vod.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@CrossOrigin
@RestController
@RequestMapping("/vod/media")
public class MediaController {

    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(
            @RequestParam("file")MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String videoId = videoService.uploadVideo(inputStream, originalFilename);
            return R.ok().message("视频上传成功").data("videoId",videoId);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error().message("视频上传失败");
        }

    }


    @DeleteMapping("remove/{vodId}")
    public R removeVideo(
            @PathVariable String vodId
    ){
        try{
            videoService.removeVideo(vodId);
            return R.ok().message("删除视频成功");
        }catch (Exception e){
            return R.error().message("删除视频失败");
        }
    }

}
