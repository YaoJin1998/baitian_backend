package com.xmut.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.cms.entity.CrmBanner;
import com.xmut.cms.mapper.CrmBannerMapper;
import com.xmut.cms.service.CrmBannerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author YaoJin
 * @since 2020-10-03
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    //查询所有banner
    //@Cacheable(key ="'selectIndexList'" ,value = "banner")  redis缓存
    @Override
    public List<CrmBanner> selectAllBanner() {
        //根据id进行降序排列，显示排列之后的前两条业务
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //last方法，拼接sql语句
        wrapper.last("limit 2");

        List<CrmBanner> list = baseMapper.selectList(wrapper);
        return list;
    }
}
