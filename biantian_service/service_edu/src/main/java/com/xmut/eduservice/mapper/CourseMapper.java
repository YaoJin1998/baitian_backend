package com.xmut.eduservice.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.eduservice.model.entity.Course;
import com.xmut.eduservice.model.entity.vo.CoursePublishVo;
import com.xmut.eduservice.model.entity.vo.CourseVo;
import com.xmut.eduservice.model.entity.vo.WebCourseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author YaoJin
 * @since 2020-09-16
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVo> selectPageByCourseQueryVo(Page<CourseVo> pageParam,
                                             @Param(Constants.WRAPPER) QueryWrapper<CourseVo> queryWrapper);

    CoursePublishVo selectCoursePublishVoById(String id);

    WebCourseVo selectWebCourseVoById(String courseId);
}
