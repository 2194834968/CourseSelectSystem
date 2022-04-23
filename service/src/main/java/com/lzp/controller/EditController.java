package com.lzp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzp.Mapper.CourseMapper;
import com.lzp.common.result.Result;
import com.lzp.model.Course;
import com.lzp.model.Teacher;
import com.lzp.vo.NewCourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.lzp.common.result.ResultCodeEnum.COURSENAME_REPEAT;

@RestController
public class EditController {
    @Autowired
    CourseMapper courseMapper;

    @PostMapping("/NewEdit")
    public Result NewEdit(NewCourseVO newCourseVO){

        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cname",newCourseVO.cname);
        Course coursetemp = courseMapper.selectOne(wrapper);

        if(coursetemp != null){
            return Result.build(COURSENAME_REPEAT);
        }
        else{
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

    @PutMapping("/edit/{cid}")
    public Result<Course> Edit(@PathVariable int cid){
        Course coursetemp = courseMapper.selectById(cid);
        return Result.ok(coursetemp);
    }

    @PostMapping("/edit")
    public String Edit(Model model,
                       HttpServletRequest request,
                       Course course){

        Teacher UserSession = (Teacher) request.getSession().getAttribute("UserSession");
        Course coursetemp = courseMapper.selectById(course.cid);
        int result;
        if(coursetemp != null){
            result = courseMapper.updateById(course);
        }
        else{
            course.selected = 0;
            result =  courseMapper.insert(course);
        }
        return "redirect:/Tmain";
    }

    @GetMapping("/delete/{cid}")
    public String DeleteEdit(Model model,
                             HttpServletRequest request,
                             @PathVariable int cid){
        int result = courseMapper.deleteById(cid);
        return "redirect:/Tmain";
    }
}
