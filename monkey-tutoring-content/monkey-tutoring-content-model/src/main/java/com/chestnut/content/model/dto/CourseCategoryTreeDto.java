package com.chestnut.content.model.dto;

import com.chestnut.content.model.po.CourseCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CourseCategoryTreeDto extends CourseCategory implements Serializable {
    /**
     * 子节点
     */
    private List<CourseCategoryTreeDto> childrenTreeNodes;
}
