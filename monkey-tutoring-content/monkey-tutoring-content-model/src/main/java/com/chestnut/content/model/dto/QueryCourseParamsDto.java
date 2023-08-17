package com.chestnut.content.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 课程查询参数Dto
 *
 * @author: Chestnut
 * @since: 2023-08-16
 **/
@Data
@ToString
public class QueryCourseParamsDto {
    /**
     * 审核状态
     */
    private String auditStatus;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 发布状态
     */
    private String publishStatus;

}
