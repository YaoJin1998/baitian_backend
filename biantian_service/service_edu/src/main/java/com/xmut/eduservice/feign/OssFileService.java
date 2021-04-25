package com.xmut.eduservice.feign;

import com.xmut.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient("service-oss")
public interface OssFileService {

    @DeleteMapping("/eduoss/fileoss/remove")
    R removeFile(@RequestBody String url);
}
