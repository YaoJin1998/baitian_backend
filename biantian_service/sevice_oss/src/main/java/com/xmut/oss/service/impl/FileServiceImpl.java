package com.xmut.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.xmut.oss.service.FileService;
import com.xmut.oss.util.ConstantPropertiesUtils;
import com.xmut.oss.util.OssPropterties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OssPropterties ossPropterties;

    @Override
    public String upload(InputStream inputStream, String module, String originalFilename) {
        //读取配置信息
        String endpoint = ossPropterties.getEndpoint();
        String keyid = ossPropterties.getKeyid();
        String keysecret = ossPropterties.getKeysecret();
        String bucketname = ossPropterties.getBucketname();


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        if (!ossClient.doesBucketExist(bucketname)){
            ossClient.createBucket(bucketname);
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }

        //构建objectName：文件路径
        String folder = new DateTime().toString("yyyy/MM/dd");//利用依赖的工具类获取时间
        String fileName = UUID.randomUUID().toString();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String key = module+"/"+folder+"/"+fileName+fileExtension;

        //获取上传文件输入流
        ossClient.putObject(bucketname,key,inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        //返回url
        return "https://"+bucketname+"."+endpoint+"/"+key;
    }

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POIND;
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;


// 上传文件流。
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取上传文件名称
            String fileName = file.getOriginalFilename();
            //1.在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName = uuid+fileName;
            //2.把文件按照日期进行分类
            String datePath = new DateTime().toString("yyyy/MM/dd");//利用依赖的工具类获取时间
            fileName = datePath+"/"+fileName;

            //bucket名称，上传到oss文件路径和文件名称，上传文件输入流
            ossClient.putObject(bucketName,fileName , inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回，需要把上传到阿里云oss路径拼接出来
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
