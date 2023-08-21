package com.chestnut.content.api;

import com.chestnut.content.model.dto.AddTeachplanDto;
import com.chestnut.content.model.dto.TeachplanDto;
import com.chestnut.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程计划 前端控制器
 * </p>
 *
 * @author Chestnut
 */
@Api(value = "教学计划管理", tags = "教学计划管理")
@RestController
public class TeachplanController {

    @Autowired
    private TeachplanService teachplanService;

    @ApiOperation("教学计划查询")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTeachplanTreeNodes(@PathVariable Long courseId) {
        return teachplanService.getTeachplanTreeNodes(courseId);
    }

    @ApiOperation("课程计划创建或修改")
    @PostMapping("/teachplan")
    public void save(@RequestBody @Validated AddTeachplanDto teachplan){
        teachplanService.saveTeachplan(teachplan);
    }

}
