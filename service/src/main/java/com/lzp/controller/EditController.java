package com.lzp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzp.Mapper.CourseMapper;
import com.lzp.common.result.Result;
import com.lzp.model.Course;
import com.lzp.model.Teacher;
import com.lzp.vo.NewCourseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.lzp.common.result.ResultCodeEnum.*;

@RestController
@RequestMapping("/edit")
public class EditController {
    @Autowired
    CourseMapper courseMapper;

    @ApiOperation(value = "提交编辑完的新课程")
    @PostMapping("/NewEdit")
    public Result NewEdit(NewCourseVO newCourseVO){

        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cname",newCourseVO.cname);
        Course coursetemp = courseMapper.selectOne(wrapper);

        if(coursetemp != null){
            return Result.build(COURSENAME_REPEAT);
        }else{
            QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("max(cid) as cid");
            Course newCourseTemp = new Course();
            newCourseTemp.cid = courseMapper.selectOne(queryWrapper).cid+1;
            newCourseTemp.cname = newCourseVO.cname;
            newCourseTemp.credit = newCourseVO.credit;
            newCourseTemp.teacher = newCourseVO.teacher;
            newCourseTemp.Slimit = newCourseVO.Slimit;
            newCourseTemp.selected = 0;
            newCourseTemp.introduce = newCourseVO.introduce;
            int result =  courseMapper.insert(newCourseTemp);
            return Result.ok();
        }
    }

    @ApiOperation(value = "获取cid对应课程，准备修改")
    @GetMapping("/{cid}")
    public Result<Course> Edit(@PathVariable int cid){
        Course coursetemp = courseMapper.selectById(cid);
        return Result.ok(coursetemp);
    }

    @ApiOperation(value = "提交修改完的课程")
    @PostMapping("/SubmitEdit")
    public Result Edit(Course course){

        Course coursetemp = courseMapper.selectById(course.cid);
        int result;
        if(coursetemp != null){
            result = courseMapper.updateById(course);
            return Result.ok();
        }else{
            return Result.fail(EDIT_FAIL);
        }
    }

    @ApiOperation(value = "删除cid指定的课程")
    @GetMapping("/delete/{cid}")
    public Result DeleteEdit(@PathVariable int cid){
        int reselt = courseMapper.deleteById(cid);
        Course coursetemp = courseMapper.selectById(cid);
        if(coursetemp == null){
            return Result.ok();
        }else{
            return Result.fail(DELETE_FAIL);
        }

    }
}
