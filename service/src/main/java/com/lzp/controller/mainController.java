package com.lzp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzp.Mapper.CourseMapper;
import com.lzp.Mapper.SCMapper;
import com.lzp.Mapper.StudentMapper;
import com.lzp.common.result.Result;
import com.lzp.model.Course;
import com.lzp.model.Student;
import com.lzp.model.sc;
import com.lzp.service.CourseService;
import com.lzp.vo.CourseQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/main")
public class mainController {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SCMapper scMapper;

    @Autowired
    CourseService courseService;

    @ApiOperation(value = "获取该学生的已选课表")
    @PostMapping("{userid}/StudentCoursePage/{current}/{limit}")
    //{current}为当前页，{limit}为页长限制
    public Result findStudentCourse(@PathVariable long userid,
                                    @PathVariable long current,
                                    @PathVariable long limit,
                                    @RequestBody(required = false) CourseQueryVo courseQueryVo){
        Page<Course> page = new Page<>(current,limit);
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        int cid = courseQueryVo.cid;
        String cname = courseQueryVo.cname;
        String teacher = courseQueryVo.teacher;
        //如果有搜索关键词，写入搜索条件
        if(cid != 0){
            wrapper.eq("cid",cid);
        }
        if(!StringUtils.isEmpty(cname)){
            wrapper.like("cname",cname);
        }
        if(!StringUtils.isEmpty(teacher)){
            wrapper.like("teacher",teacher);
        }

        //如果该学生有已选课，写入搜索条件
        sc usersc = scMapper.selectById(userid);
        if(usersc != null){
            wrapper.eq("cid",usersc.cid1);
        }


        Page<Course> CoursePage = courseService.page(page,wrapper);
        return Result.ok(CoursePage);

    }

    @ApiOperation(value = "获取该学生的未选课表")
    @PostMapping("{userid}/CoursePage/{current}/{limit}")
    public Result findPageCourse(@PathVariable long userid,
                                 @PathVariable long current,
                                 @PathVariable long limit,
                                 @RequestBody(required = false) CourseQueryVo courseQueryVo){
        Page<Course> page = new Page<>(current,limit);
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        int cid = courseQueryVo.cid;
        String cname = courseQueryVo.cname;
        String teacher = courseQueryVo.teacher;
        //如果有搜索关键词，写入搜索条件
        if(cid != 0){
            wrapper.eq("cid",cid);
        }
        if(!StringUtils.isEmpty(cname)){
            wrapper.like("cname",cname);
        }
        if(!StringUtils.isEmpty(teacher)){
            wrapper.like("teacher",teacher);
        }

        //如果该学生有已选课，写入搜索条件
        sc usersc = scMapper.selectById(userid);
        if(usersc != null){
            wrapper.ne("cid",usersc.cid1);
        }


        Page<Course> CoursePage = courseService.page(page,wrapper);
        return Result.ok(CoursePage);
    }

}
