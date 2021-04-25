package com.xmut.eduservice.controller.admin;

import com.xmut.commonutils.R;
import com.xmut.eduservice.feign.VodMediaService;
import com.xmut.eduservice.model.entity.Video;
import com.xmut.eduservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping(value ="/admin/edu/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("save")
    public R save(
            @RequestBody Video video
            ){
        boolean result = videoService.save(video);
        video.setGmtCreate(new Date());
        video.setGmtModified(new Date());
        if (result){
            return R.ok().message("保存成功");
        }else {
            return R.error().message("保存失败");
        }
    }


    @GetMapping("get/{id}")
    public R getById(
            @PathVariable String id
    ){
        Video video = videoService.getById(id);
        if (video !=null){
            return R.ok().data("item",video);
        }else {
            return R.error().message("数据不存在");
        }
    }



    @PutMapping("update")
    public R updateById(
            @RequestBody Video video
    ){
        boolean result = videoService.updateById(video);
        video.setGmtModified(new Date());
        if (result){
            return R.ok().message("修改成功");
        }else {
            return R.error().message("数据不存在");
        }
    }

    @DeleteMapping("remove/{id}")
    public R removeById(
            @PathVariable String id
    ){
        //根据videoid找到视频id
        videoService.removeMediaVideoById(id);
        boolean result = videoService.removeById(id);
        if (result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("数据不存在");
        }
    }




}
