package com.chestnut.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chestnut.content.mapper.TeachplanMapper;
import com.chestnut.content.model.dto.AddTeachplanDto;
import com.chestnut.content.model.dto.TeachplanDto;
import com.chestnut.content.model.po.Teachplan;
import com.chestnut.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程计划 服务实现类
 * </p>
 *
 * @author Chestnut
 */
@Slf4j
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements TeachplanService {

    /**
     * 根据课程id查询课程计划
     *
     * @param courseId
     * @return
     */
    @Override
    public List<TeachplanDto> getTeachplanTreeNodes(Long courseId) {
        return this.baseMapper.selectTreeNodes(courseId);
    }

    /**
     * 保存/修改课程计划
     *
     * @param addTeachplanDto
     */
    @Override
    public void saveTeachplan(AddTeachplanDto addTeachplanDto) {
        // 判断未新增/修改
        Long teachplanId = addTeachplanDto.getId();
        if (teachplanId == null) {
            // 新增
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(addTeachplanDto, teachplan);
            LambdaQueryWrapper<Teachplan> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Teachplan::getParentid, addTeachplanDto.getParentid());
            lqw.eq(Teachplan::getCourseId, addTeachplanDto.getCourseId());
            teachplan.setOrderby(this.baseMapper.selectCount(lqw) + 1);
            this.baseMapper.insert(teachplan);
        } else {
            // 修改
            Teachplan teachplan = this.baseMapper.selectById(teachplanId);
            BeanUtils.copyProperties(addTeachplanDto, teachplan);
            this.baseMapper.updateById(teachplan);
        }
    }
}
