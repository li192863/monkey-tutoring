package com.chestnut.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chestnut.base.model.PageParams;
import com.chestnut.base.model.PageResult;
import com.chestnut.content.model.dto.AddCourseDto;
import com.chestnut.content.model.dto.CourseDto;
import com.chestnut.content.model.dto.EditCourseDto;
import com.chestnut.content.model.dto.QueryCourseParamsDto;
import com.chestnut.content.model.po.CourseBase;

/**
 * <p>
 * 课程基本信息 服务类
 * </p>
 *
 * @author Chestnut
 * @since 2023-08-16
 */
public interface CourseBaseService extends IService<CourseBase> {

    /**
     * 课程分页查询
     *
     * @param pageParams
     * @param dto
     */
    PageResult<CourseBase> page(PageParams pageParams, QueryCourseParamsDto dto);

    /**
     * 新增课程
     *
     * @param companyId
     * @param dto
     * @return
     */
    CourseDto saveCourseInfo(Long companyId, AddCourseDto dto);

    /**
     * 根据id查询课程
     *
     * @param courseId
     * @return
     */
    CourseDto getCourseInfoById(Long courseId);

    /**
     * 修改课程
     * @param companyId
     * @param dto
     * @return
     */
    CourseDto updateCourseInfo(Long companyId, EditCourseDto dto);
}
