package com.xmut.cms.controller;


import com.xmut.cms.entity.CrmBanner;
import com.xmut.cms.service.CrmBannerService;
import com.xmut.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;
    //查询所有banner
    @GetMapping("/getAllBanner")
    public R getAllBanner(){
        List<CrmBanner> list = bannerService.selectAllBanner();
        return R.ok().data("list",list);
    }
}

