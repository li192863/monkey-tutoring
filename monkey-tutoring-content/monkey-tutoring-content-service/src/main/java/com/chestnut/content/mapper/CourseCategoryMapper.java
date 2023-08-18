package com.chestnut.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chestnut.content.model.dto.CourseCategoryTreeDto;
import com.chestnut.content.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author Chestnut
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {

    /**
     * 查询分类
     * @param id
     * @return
     */
    List<CourseCategoryTreeDto> selectTreeNodes(String id);

}
