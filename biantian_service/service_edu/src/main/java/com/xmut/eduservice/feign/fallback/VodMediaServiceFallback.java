package com.xmut.eduservice.feign.fallback;

import com.xmut.commonutils.R;
import com.xmut.eduservice.feign.VodMediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VodMediaServiceFallback implements VodMediaService {
    @Override
    public R removeVideo(String vodId) {
        log.info("熔断保护");
        return R.error();
    }
}
