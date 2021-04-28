package com.xmut.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.eduservice.model.entity.Course;
import com.xmut.eduservice.model.entity.form.CourseInfoForm;
import com.xmut.eduservice.model.entity.vo.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author YaoJin
 * @since 2020-09-16
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfoByIf(CourseInfoForm courseInfoForm);

    IPage<CourseVo> selectPage(long page, long limit, CourseQueryVo courseQueryVo);

    boolean removeCoverById(String id);

    boolean removeCourseById(String id);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    List<Course> webSelectList(WebCourseQueryVo webCourseQueryVo);

    WebCourseVo selectWebCourseVoById(String id);

    List<Course> selectHotCourse();
}
