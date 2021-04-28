package com.xmut.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.commonutils.R;
import com.xmut.eduservice.feign.OssFileService;
import com.xmut.eduservice.mapper.*;
import com.xmut.eduservice.model.entity.*;
import com.xmut.eduservice.model.entity.form.CourseInfoForm;
import com.xmut.eduservice.model.entity.vo.*;
import com.xmut.eduservice.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author YaoJin
 * @since 2020-09-16
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CourseCollectMapper courseCollectMapper;



    @Autowired
    private OssFileService ossFileService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存Course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        course.setStatus(Course.COURSE_DRAFT);
        course.setGmtCreate(new Date());
        course.setGmtModified(new Date());
        baseMapper.insert(course);

        //保存CourseDescripito
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescription.setGmtCreate(new Date());
        courseDescription.setGmtModified(new Date());
        courseDescriptionMapper.insert(courseDescription);
        return course.getId();

    }

    @Override
    public CourseInfoForm getCourseInfoById(String id) {
        Course course = baseMapper.selectById(id);
        if (course == null){
            return  null;
        }
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);

        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course,courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfoByIf(CourseInfoForm courseInfoForm) {
        //更新Course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        course.setStatus(Course.COURSE_DRAFT);
        course.setGmtModified(new Date());
        baseMapper.updateById(course);

        //保存CourseDescripito
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(courseInfoForm.getId());
        courseDescription.setGmtModified(new Date());
        courseDescriptionMapper.updateById(courseDescription);
    }

    @Override
    public IPage<CourseVo> selectPage(long page, long limit, CourseQueryVo courseQueryVo) {
        QueryWrapper<CourseVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("c.gmt_create");

        String title = courseQueryVo.getTitle();
        String teacherId = courseQueryVo.getTeacherId();
        String subjectParentId = courseQueryVo.getSubjectParentId();
        String subjectId = courseQueryVo.getSubjectId();

        if (!StringUtils.isEmpty(title)){
            queryWrapper.like("c.title",title);
        }
        if (!StringUtils.isEmpty(teacherId)){
            queryWrapper.eq("c.teacher_id",teacherId);
        }
        if (!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.like("c.subject_parent_id",subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)){
            queryWrapper.like("c.subject_id",subjectId);
        }

        //组装分页
        Page<CourseVo> pageParam = new Page<>(page, limit);

        //执行查询
        //只需要在mapper层传入封装好的分页组件即可
        List<CourseVo> records =  baseMapper.selectPageByCourseQueryVo(pageParam,queryWrapper);

        //将records设置到pageParam中
        return pageParam.setRecords(records);
    }

    @Override
    public boolean removeCoverById(String id) {
        Course course = baseMapper.selectById(id);
        if (course != null){
            String cover = course.getCover();
            if (!StringUtils.isEmpty(cover)){
                R r = ossFileService.removeFile(cover);
                return r.getSuccess();
            }
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeCourseById(String id) {

        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        videoMapper.delete(videoQueryWrapper);

        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",id);
        chapterMapper.delete(chapterQueryWrapper);

        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("course_id",id);
        commentMapper.delete(commentQueryWrapper);

        QueryWrapper<CourseCollect> courseCollectQueryWrapper = new QueryWrapper<>();
        courseCollectQueryWrapper.eq("course_id",id);
        courseCollectMapper.delete(courseCollectQueryWrapper);


        courseDescriptionMapper.deleteById(id);

        return this.removeById(id);


    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {

        return baseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_NORMAL);
        course.setGmtModified(new Date());
        course.setGmtCreate(new Date());
        return this.updateById(course);
    }

    @Override
    public List<Course> webSelectList(WebCourseQueryVo webCourseQueryVo) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",Course.COURSE_NORMAL);
        if (!StringUtils.isEmpty(webCourseQueryVo.getSubjectParentId())){
            queryWrapper.eq("subject_parent_id",webCourseQueryVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(webCourseQueryVo.getSubjectId())){
            queryWrapper.eq("subject_id",webCourseQueryVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(webCourseQueryVo.getBuyCountSort())){
            queryWrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(webCourseQueryVo.getGmtCreateSort())){
            queryWrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(webCourseQueryVo.getPriceSort())){
            queryWrapper.orderByDesc("price");
        }
        return baseMapper.selectList(queryWrapper);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public WebCourseVo selectWebCourseVoById(String id){
        Course course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
        return baseMapper.selectWebCourseVoById(id);
    }

    @Override
    public List<Course> selectHotCourse() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 8");

        return baseMapper.selectList(queryWrapper);
    }
}
