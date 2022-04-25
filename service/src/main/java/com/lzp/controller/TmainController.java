package com.lzp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzp.Mapper.CourseMapper;
import com.lzp.Mapper.TeacherMapper;
import com.lzp.common.result.Result;
import com.lzp.model.Course;
import com.lzp.model.sc;
import com.lzp.service.CourseService;
import com.lzp.vo.CourseQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.lzp.common.result.ResultCodeEnum.COURSE_SELECTED;

@Controller
public class TmainController {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    CourseService courseService;

    @ApiOperation(value = "获取该老师所授课表")
    @PostMapping("{userid}/TeacherCoursePage/{current}/{limit}")
    //{current}为当前页，{limit}为页长限制
    public Result findTeacherCourse(@PathVariable int userid,
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

        //将该老师id写入搜索条件，搜索该老师选课
        wrapper.eq("tid",userid);
        Page<Course> CoursePage = courseService.page(page,wrapper);
        return Result.ok(CoursePage);
    }

    @ApiOperation(value = "获取所有选课课表")
    @PostMapping("/AllCoursePage/{current}/{limit}")
    //{current}为当前页，{limit}为页长限制
    public Result AllCourse(@PathVariable long current,
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

        Page<Course> CoursePage = courseService.page(page,wrapper);
        return Result.ok(CoursePage);
    }
}
