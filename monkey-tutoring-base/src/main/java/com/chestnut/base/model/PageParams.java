package com.chestnut.base.model;

import lombok.Data;
import lombok.ToString;

/**
 * 分页查询通用参数
 *
 * @author: Chestnut
 * @since: 2023-08-16
 **/
@Data
@ToString
public class PageParams {

    /**
     * 当前页码
     */
    private Long pageNo = 1L;

    /**
     * 每页记录数默认值
     */
    private Long pageSize = 10L;

    public PageParams() {

    }

    public PageParams(Long pageNo, Long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
