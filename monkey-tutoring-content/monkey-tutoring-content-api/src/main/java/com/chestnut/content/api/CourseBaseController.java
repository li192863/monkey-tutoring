package com.chestnut.content.api;

import com.chestnut.base.model.PageParams;
import com.chestnut.base.model.PageResult;
import com.chestnut.content.model.dto.QueryCourseParamsDto;
import com.chestnut.content.model.po.CourseBase;
import com.chestnut.content.service.CourseBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程控制器
 *
 * @author: Chestnut
 * @since: 2023-08-16
 **/
@Api(value = "课程信息管理接口", tags = "课程信息管理接口")
@RestController
public class CourseBaseController {
    @Autowired
    private CourseBaseService courseBaseService;

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody(required = false) QueryCourseParamsDto queryCourseParams) {
        return courseBaseService.queryCourseBaseList(pageParams, queryCourseParams);
    }

}
