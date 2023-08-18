package com.chestnut.content.api;

import com.chestnut.base.exception.ValidationGroups;
import com.chestnut.base.model.PageParams;
import com.chestnut.base.model.PageResult;
import com.chestnut.content.model.dto.AddCourseDto;
import com.chestnut.content.model.dto.CourseDto;
import com.chestnut.content.model.dto.EditCourseDto;
import com.chestnut.content.model.dto.QueryCourseParamsDto;
import com.chestnut.content.model.po.CourseBase;
import com.chestnut.content.service.CourseBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "课程信息管理接口", tags = "课程信息管理接口")
@RestController
public class CourseBaseController {
    @Autowired
    private CourseBaseService courseBaseService;

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> page(PageParams pageParams, @RequestBody(required = false) QueryCourseParamsDto queryCourseParams) {
        return courseBaseService.page(pageParams, queryCourseParams);
    }

    @ApiOperation("新增课程")
    @PostMapping("/course")
    public CourseDto save(@RequestBody @Validated(ValidationGroups.Insert.class) AddCourseDto addCourseDto) {
        // TODO: 设置companyId
        long companyId = 1232141425L;
        return courseBaseService.saveCourseInfo(companyId, addCourseDto);
    }

    @ApiOperation("根据id查询课程")
    @GetMapping("/course/{courseId}")
    public CourseDto getById(@PathVariable Long courseId) {
        return courseBaseService.getCourseInfoById(courseId);
    }

    @ApiOperation("修改课程")
    @PutMapping("/course")
    public CourseDto update(@RequestBody @Validated EditCourseDto editCourseDto) {
        // TODO: 设置companyId
        long companyId = 1232141425L;
        return courseBaseService.updateCourseInfo(companyId, editCourseDto);
    }

}
