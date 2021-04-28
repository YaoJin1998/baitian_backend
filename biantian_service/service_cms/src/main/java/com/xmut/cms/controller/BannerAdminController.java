package com.xmut.cms.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.cms.entity.CrmBanner;
import com.xmut.cms.service.CrmBannerService;
import com.xmut.commonutils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author YaoJin
 * @since 2020-10-03
 */
@CrossOrigin
@RestController
@RequestMapping("/educms/banneradmin")
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;


    //分页查询banner
    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@ApiParam(name = "page", value = "当前页码", required = true)
                            @PathVariable long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable long limit){
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,null);
        return R.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    //获取banner
    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item",banner);
    }

    //添加banner
    @ApiOperation(value = "添加banner")
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        bannerService.save(crmBanner);
        return R.ok();
    }

    //修改banner
    @ApiOperation(value = "修改banner")
    @PutMapping("update")
    public R updateById9(@RequestBody CrmBanner crmBanner){
        bannerService.updateById(crmBanner);
        return R.ok();
    }

    //删除banner
    @ApiOperation(value = "删除banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id){
        bannerService.removeById(id);
        return R.ok();
    }

}

