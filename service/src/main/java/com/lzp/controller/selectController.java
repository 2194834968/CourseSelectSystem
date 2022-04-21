package com.lzp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzp.Mapper.CourseMapper;
import com.lzp.Mapper.SCMapper;
import com.lzp.Mapper.StudentMapper;
import com.lzp.common.result.Result;
import com.lzp.model.Course;
import com.lzp.model.Student;
import com.lzp.model.sc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class selectController {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SCMapper scMapper;

    @GetMapping("/select/{cid}")
    public String select(Model model,
                         HttpServletRequest request,
                         @PathVariable int cid){

        //查询该用户已选课表
        Student UserSession = (Student) request.getSession().getAttribute("UserSession");
        sc usersc = scMapper.selectById(UserSession.userid);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();

        if (usersc != null) {
            //如果该用户已有选课
            model.addAttribute("msg","你已经选了一门课！");
        }else{
            //更新选课表
            sc scTemp = new sc(UserSession.userid,cid);
            int result = scMapper.insert(scTemp);
            //更新课程表
            Course courseTemp = courseMapper.selectById(cid);
            courseTemp.selected = courseTemp.selected + 1;
            result =  courseMapper.updateById(courseTemp);
            //System.out.println(result);
        }

        //重新加载main页面
        return "redirect:/main";
    }

    @GetMapping("/deselect/{cid}")
    public String deselect(Model model,
                         HttpServletRequest request,
                         @PathVariable int cid){
        //查询该用户已选课表
        Student UserSession = (Student) request.getSession().getAttribute("UserSession");
        sc usersc = scMapper.selectById(UserSession.userid);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();

        //更新选课表
        int result = scMapper.deleteById(UserSession.userid);
        //更新课程表
        Course courseTemp = courseMapper.selectById(cid);
        courseTemp.selected = courseTemp.selected - 1;

        result =  courseMapper.updateById(courseTemp);
        //System.out.println(result);

        //重新加载main页面
        return "redirect:/main";
    }

}
