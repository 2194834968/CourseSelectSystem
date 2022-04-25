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

import static com.lzp.common.result.ResultCodeEnum.COURSE_SELECTED;

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
    public Result findStudentCourse(@PathVariable int userid,
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
        QueryWrapper<sc> scWrapper = new QueryWrapper<>();
        scWrapper.eq("sid",userid);
        List<sc> usersc = scMapper.selectList(scWrapper);
        for(sc i : usersc){
        }


        Page<Course> CoursePage = courseService.page(page,wrapper);
        return Result.ok(CoursePage);

    }

    @ApiOperation(value = "获取该学生的未选课表")
    @PostMapping("{userid}/CoursePage/{current}/{limit}")
    public Result findPageCourse(@PathVariable int userid,
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
        QueryWrapper<sc> scWrapper = new QueryWrapper<>();
        scWrapper.eq("sid",userid);
        List<sc> usersc = scMapper.selectList(scWrapper);
        for(sc i : usersc){
        }


        Page<Course> CoursePage = courseService.page(page,wrapper);
        return Result.ok(CoursePage);
    }

}
