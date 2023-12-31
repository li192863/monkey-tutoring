package com.chestnut.content.api;

import com.chestnut.content.model.dto.CourseCategoryTreeDto;
import com.chestnut.content.service.CourseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "课程分类管理", tags = "课程分类管理")
@RestController
public class CourseCategoryController {
    @Autowired
    private CourseCategoryService courseCategoryService;

    @ApiOperation("课程分类查询")
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> getCategoryTreeNodes() {
        return courseCategoryService.getCategoryTreeNodes("1");
    }
}
