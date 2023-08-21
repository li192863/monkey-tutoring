package com.chestnut.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chestnut.content.model.dto.TeachplanDto;
import com.chestnut.content.model.po.Teachplan;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author Chestnut
 */
public interface TeachplanMapper extends BaseMapper<Teachplan> {
    /**
     * 查询教学计划
     * @param courseId
     * @return
     */
    List<TeachplanDto> selectTreeNodes(Long courseId);
}
