package com.xmut.oss.controller;


import com.xmut.commonutils.R;
import com.xmut.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@CrossOrigin
@RestController
@RequestMapping("/eduoss/fileoss")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public R upload(
                    @RequestParam("file")
                    MultipartFile file,
            @RequestParam("module")
                    String module) throws IOException {

        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String uploadUrl = fileService.upload(inputStream, module, originalFilename);

        return R.ok().message("文件上传成功").data("url",uploadUrl);
    }

    @PostMapping("")
    public R uploadOssFile(MultipartFile file){
        //获取上传的文件
        String url = fileService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
