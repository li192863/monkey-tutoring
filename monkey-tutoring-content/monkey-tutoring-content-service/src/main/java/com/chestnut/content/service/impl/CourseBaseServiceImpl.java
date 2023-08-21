package com.chestnut.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chestnut.base.exception.MonkeyTutoringException;
import com.chestnut.base.model.PageParams;
import com.chestnut.base.model.PageResult;
import com.chestnut.content.mapper.CourseBaseMapper;
import com.chestnut.content.mapper.CourseCategoryMapper;
import com.chestnut.content.mapper.CourseMarketMapper;
import com.chestnut.content.model.dto.AddCourseDto;
import com.chestnut.content.model.dto.CourseDto;
import com.chestnut.content.model.dto.EditCourseDto;
import com.chestnut.content.model.dto.QueryCourseParamsDto;
import com.chestnut.content.model.po.CourseBase;
import com.chestnut.content.model.po.CourseMarket;
import com.chestnut.content.service.CourseBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程基本信息 服务实现类
 * </p>
 *
 * @author Chestnut
 */
@Slf4j
@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase> implements CourseBaseService {

    @Autowired
    private CourseMarketMapper courseMarketMapper;

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    /**
     * 分页查询
     *
     * @param pageParams
     * @param dto
     */
    public PageResult<CourseDto> page(PageParams pageParams, QueryCourseParamsDto dto) {
        LambdaQueryWrapper<CourseBase> courseBaseLqw = new LambdaQueryWrapper<>();
        courseBaseLqw.like(StringUtils.isNotEmpty(dto.getCourseName()), CourseBase::getName, dto.getCourseName());
        courseBaseLqw.eq(StringUtils.isNotEmpty(dto.getAuditStatus()), CourseBase::getAuditStatus, dto.getAuditStatus());
        courseBaseLqw.eq(StringUtils.isNotEmpty(dto.getPublishStatus()), CourseBase::getStatus, dto.getPublishStatus());

        // 分页查询
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<CourseBase> pageResult = baseMapper.selectPage(page, courseBaseLqw);
        // 组装结果
        Page<CourseDto> res = new Page<>();
        List<CourseDto> records = pageResult.getRecords().stream().map(item -> {
            CourseDto course = new CourseDto();
            BeanUtils.copyProperties(item, course);
            LambdaQueryWrapper<CourseMarket> courseMarketLqw = new LambdaQueryWrapper<>();
            courseMarketLqw.eq(CourseMarket::getId, item.getId());
            CourseMarket courseMarket = courseMarketMapper.selectOne(courseMarketLqw);
            if (courseMarket == null) {
                course.setCharge("201000");
            } else {
                course.setCharge("201001");
            }
            return course;
        }).collect(Collectors.toList());
        res.setRecords(records);
        // 组装结果
        long total = pageResult.getTotal();
        return new PageResult<>(records, total, pageParams.getPageNo(), pageParams.getPageSize());
    }

    /**
     * 新增课程
     *
     * @param companyId
     * @param dto
     * @return
     */
    @Override
    public CourseDto saveCourseInfo(Long companyId, AddCourseDto dto) {
        // 基本信息
        CourseBase courseBase = new CourseBase();
        BeanUtils.copyProperties(dto, courseBase);
        courseBase.setCompanyId(companyId);
        courseBase.setCreateDate(LocalDateTime.now());
        courseBase.setAuditStatus("202002");
        courseBase.setStatus("203001");
        int insert = this.baseMapper.insert(courseBase);
        if (insert <= 0) {
            MonkeyTutoringException.cast("添加课程信息失败");
        }
        // 营销信息
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(dto, courseMarket);
        courseMarket.setId(courseBase.getId());
        insert = saveCourseMarketInfo(courseMarket);
        if (insert <= 0) {
            MonkeyTutoringException.cast("添加课程信息失败");
        }
        return getCourseInfoById(courseBase.getId());
    }

    /**
     * 保存课程营销信息
     *
     * @param courseMarket
     * @return
     */
    private int saveCourseMarketInfo(CourseMarket courseMarket) {
        // 检查是否合法
        String charge = courseMarket.getCharge();
        if (charge.equals("201001")) {
            if (courseMarket.getPrice() == null || courseMarket.getPrice() <= 0.0) {
                MonkeyTutoringException.cast("课程价格信息不合法");
            }
        }
        // 保存营销信息
        Long id = courseMarket.getId();
        CourseMarket courseMarketOld = courseMarketMapper.selectById(id);
        if (courseMarketOld == null) {
            return courseMarketMapper.insert(courseMarket);
        } else {
            BeanUtils.copyProperties(courseMarket, courseMarketOld);
            courseMarket.setId(id);
            return courseMarketMapper.updateById(courseMarket);
        }
    }

    /**
     * 获取课程信息
     *
     * @param courseId
     * @return
     */
    public CourseDto getCourseInfoById(Long courseId) {
        // 基本信息
        CourseDto res = new CourseDto();
        CourseBase courseBase = this.baseMapper.selectById(courseId);
        if (courseBase != null) {
            BeanUtils.copyProperties(courseBase, res);
        } else {
            return null;
        }
        // 营销信息
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        if (courseMarket != null) {
            BeanUtils.copyProperties(courseMarket, res);
        }
        // 其他信息
        res.setMtName(courseCategoryMapper.selectById(courseBase.getMt()).getName());
        res.setStName(courseCategoryMapper.selectById(courseBase.getSt()).getName());
        return res;
    }

    /**
     * 修改课程
     *
     * @param companyId
     * @param dto
     * @return
     */
    @Override
    public CourseDto updateCourseInfo(Long companyId, EditCourseDto dto) {
        // 基本信息
        Long courseId = dto.getId();
        CourseBase courseBase = this.baseMapper.selectById(courseId);
        if (courseBase == null) {
            MonkeyTutoringException.cast("课程不存在");
        }
        if (!companyId.equals(courseBase.getCompanyId())) {
            MonkeyTutoringException.cast("本机构只可修改本机构数据");
        }
        BeanUtils.copyProperties(dto, courseBase);
        courseBase.setChangeDate(LocalDateTime.now());
        int i = this.baseMapper.updateById(courseBase);
        if (i <= 0) {
            MonkeyTutoringException.cast("修改课程失败");
        }
        // 营销信息
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(dto, courseMarket);
        courseMarket.setId(courseBase.getId());
        saveCourseMarketInfo(courseMarket);
        return getCourseInfoById(courseId);
    }
}
