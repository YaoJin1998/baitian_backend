package com.xmut.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.eduservice.model.entity.Chapter;
import com.xmut.eduservice.model.entity.Course;
import com.xmut.eduservice.model.entity.form.CourseInfoForm;
import com.xmut.eduservice.model.entity.vo.ChapterVo;
import com.xmut.eduservice.model.entity.vo.CoursePublishVo;
import com.xmut.eduservice.model.entity.vo.CourseQueryVo;
import com.xmut.eduservice.model.entity.vo.CourseVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author YaoJin
 * @since 2020-09-16
 */
public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);

}
