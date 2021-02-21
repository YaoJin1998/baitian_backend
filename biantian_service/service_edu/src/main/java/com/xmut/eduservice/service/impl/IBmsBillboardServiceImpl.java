package com.xmut.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.eduservice.mapper.BmsBillboardMapper;
import com.xmut.eduservice.model.entity.BmsBillboard;
import com.xmut.eduservice.service.IBmsBillboardService;
import org.springframework.stereotype.Service;

@Service
public class IBmsBillboardServiceImpl extends ServiceImpl<BmsBillboardMapper
        , BmsBillboard> implements IBmsBillboardService {

}
