package com.lzp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class mainController {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SCMapper scMapper;

    @Autowired
    CourseService courseService;

    @GetMapping(value = {"/main"})
    public String main(Model model,
                       HttpServletRequest request){

        Student UserSession = (Student) request.getSession().getAttribute("UserSession");
        sc usersc = scMapper.selectById(UserSession.userid);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        //搜索已经选了的课
        if(usersc != null){
            Course SelectedCourse = courseMapper.selectById(usersc.cid1);
            model.addAttribute("SelectedCourse",SelectedCourse);
            queryWrapper.ne("cid",usersc.cid1);
        }

        //显示可选课表
        List<Course> course = courseMapper.selectList(queryWrapper);
        model.addAttribute("course",course);

        if(usersc != null){
            return "main";
        } else{
            return "desmain";
        }
    }

    @GetMapping(value = {"/sindex"})
    public String sindex(Model model,
                         HttpServletRequest request){
        Student UserSession = (Student) request.getSession().getAttribute("UserSession");
        sc usersc = scMapper.selectById(UserSession.userid);
        //搜索已经选了的课
        if(usersc != null){
            Course SelectedCourse = courseMapper.selectById(usersc.cid1);
            model.addAttribute("SelectedCourse",SelectedCourse);
        } else{
            return "desindex";
        }
        return "sindex";
    }

    @ApiOperation(value = "获取分页的选课表", notes = "test")
    @PostMapping("CoursePage/{current}/{limit}")
    public Result fingPageCourse(@PathVariable long current,
                                 @PathVariable long limit,
                                 @RequestBody(required = false) CourseQueryVo courseQueryVo){
        Page<Course> page = new Page<>(current,limit);
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        int cid = courseQueryVo.cid;
        String cname = courseQueryVo.cname;
        String teacher = courseQueryVo.teacher;
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
