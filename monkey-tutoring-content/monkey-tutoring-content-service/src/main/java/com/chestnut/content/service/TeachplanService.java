package com.chestnut.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chestnut.content.model.dto.AddTeachplanDto;
import com.chestnut.content.model.dto.TeachplanDto;
import com.chestnut.content.model.po.Teachplan;

import java.util.List;

/**
 * <p>
 * 课程计划 服务类
 * </p>
 *
 * @author Chestnut
 * @since 2023-08-16
 */
public interface TeachplanService extends IService<Teachplan> {
    /**
     * 根据课程id查询课程计划
     * @param courseId
     * @return
     */
    List<TeachplanDto> getTeachplanTreeNodes(Long courseId);

    /**
     * 保存/修改课程计划
     * @param addTeachplanDto
     */
    void saveTeachplan(AddTeachplanDto addTeachplanDto);
}
