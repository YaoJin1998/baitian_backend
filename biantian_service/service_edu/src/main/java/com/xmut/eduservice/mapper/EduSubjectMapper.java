package com.xmut.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmut.eduservice.model.entity.EduSubject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author YaoJin
 * @since 2020-09-13
 */
@Mapper
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

    List<EduSubject> selectNestedListByParentId(String s);
}
