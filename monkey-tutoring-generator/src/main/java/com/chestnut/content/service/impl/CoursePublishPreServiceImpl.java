package com.chestnut.content.service.impl;

import com.chestnut.content.model.po.CoursePublishPre;
import com.chestnut.content.mapper.CoursePublishPreMapper;
import com.chestnut.content.service.CoursePublishPreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 课程发布 服务实现类
 * </p>
 *
 * @author Chestnut
 */
@Slf4j
@Service
public class CoursePublishPreServiceImpl extends ServiceImpl<CoursePublishPreMapper, CoursePublishPre> implements CoursePublishPreService {

}
