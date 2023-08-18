package com.chestnut.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chestnut.content.mapper.CourseCategoryMapper;
import com.chestnut.content.model.dto.CourseCategoryTreeDto;
import com.chestnut.content.model.po.CourseCategory;
import com.chestnut.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author Chestnut
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {
    @Override
    public List<CourseCategoryTreeDto> getTreeNodes(String id) {
        // 递归查询
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = this.baseMapper.selectTreeNodes(id);
        // 构建Map
        Map<String, CourseCategoryTreeDto> map = courseCategoryTreeDtos.stream().
                filter(item -> !id.equals(item.getId()))
                .collect(Collectors.toMap(CourseCategory::getId, value -> value, (key1, key2) -> key2));
        // 返回结果
        List<CourseCategoryTreeDto> res = new ArrayList<>();
        courseCategoryTreeDtos.stream().filter(item -> !id.equals(item.getId())).forEach(item -> {
            if (item.getParentid().equals(id)) {
                res.add(item);
            }
            // 向父节点插入孩子
            CourseCategoryTreeDto parent = map.get(item.getParentid());
            if (parent != null) {
                if (parent.getChildrenTreeNodes() == null) {
                    parent.setChildrenTreeNodes(new ArrayList<>());
                }
                parent.getChildrenTreeNodes().add(item);
            }
        });
        return res;
    }
}
