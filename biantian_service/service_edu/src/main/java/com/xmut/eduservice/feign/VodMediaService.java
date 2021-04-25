package com.xmut.eduservice.feign;

import com.xmut.commonutils.R;
import com.xmut.eduservice.feign.fallback.VodMediaServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "service-vod",fallback = VodMediaServiceFallback.class)
public interface VodMediaService {

    @DeleteMapping("/vod/media/remove/{vodId}")
    R removeVideo(@PathVariable("vodId") String vodId);
}
