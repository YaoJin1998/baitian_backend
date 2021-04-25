package com.xmut.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmut.eduservice.model.entity.CourseDescription;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程简介 Mapper 接口
 * </p>
 *
 * @author YaoJin
 * @since 2020-09-16
 */
@Repository
public interface CourseDescriptionMapper extends BaseMapper<CourseDescription> {

}
