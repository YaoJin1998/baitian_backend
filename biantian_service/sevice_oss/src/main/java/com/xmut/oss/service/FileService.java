package com.xmut.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {

    String upload(InputStream inputStream,String module,String originalFilename);

    String uploadFileAvatar(MultipartFile file);

}
