package com.lzp.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzp.Mapper.CourseMapper;
import com.lzp.model.Course;
import com.lzp.service.CourseService;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
}
