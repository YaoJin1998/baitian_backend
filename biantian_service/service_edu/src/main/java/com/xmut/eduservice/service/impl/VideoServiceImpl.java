package com.xmut.eduservice.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xmut.eduservice.feign.VodMediaService;
import com.xmut.eduservice.mapper.*;
import com.xmut.eduservice.model.entity.*;

import com.xmut.eduservice.service.VideoService;
import com.xmut.eduservice.util.AliyunVodSDKUtils;
import com.xmut.eduservice.util.VodProperties;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;

import java.io.InputStream;


/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author YaoJin
 * @since 2020-09-16
 */
@Service
@Slf4j
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodMediaService vodMediaService;

    @Override
    public void removeMediaVideoById(String id) {
        Video video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        vodMediaService.removeVideo(videoSourceId);
    }
    @Autowired
    private VodProperties vodProperties;

    @Override
    public String uploadVideo(InputStream inputStream, String originalFilename) {
        String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

        UploadStreamRequest request = new UploadStreamRequest(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret(),
                title,originalFilename,inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        String videoId = response.getVideoId();
        if (StringUtils.isEmpty(videoId)){
            log.error("阿里云上传失败"+ response.getCode()+"-"+response.getMessage());
        }
        return videoId;
    }

    @Override
    public void removeVideo(String videoId) throws ClientException {
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                vodProperties.getKeyid(),vodProperties.getKeysecret()
        );

        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        client.getAcsResponse(request);

    }

    @Override
    public String getPlayAuth(String videoSourceId) throws ClientException {
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                vodProperties.getKeyid(),vodProperties.getKeysecret()
        );

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoSourceId);
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        return response.getPlayAuth();

    }


    public static DeleteVideoResponse deleteVideo(DefaultAcsClient client) throws Exception{
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds("VideoId1,VideoId2");
        return client.getAcsResponse(request);
    }
}
