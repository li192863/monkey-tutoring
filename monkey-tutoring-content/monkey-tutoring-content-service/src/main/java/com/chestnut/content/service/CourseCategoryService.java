package com.chestnut.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chestnut.content.model.dto.CourseCategoryTreeDto;
import com.chestnut.content.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 服务类
 * </p>
 *
 * @author Chestnut
 * @since 2023-08-16
 */
public interface CourseCategoryService extends IService<CourseCategory> {

    /**
     * 查询分类
     * @param id
     * @return
     */
    List<CourseCategoryTreeDto> getCategoryTreeNodes(String id);
}
