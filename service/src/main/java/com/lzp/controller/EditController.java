package com.lzp.controller;

import com.lzp.Mapper.CourseMapper;
import com.lzp.model.Course;
import com.lzp.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EditController {
    @Autowired
    CourseMapper courseMapper;

    @GetMapping("/NewEdit")
    public String Edit(Model model,
                       HttpServletRequest request){
        Course coursetemp = new Course();
        model.addAttribute("coursetemp",coursetemp);
        return "NewEdit";
    }

    @PostMapping("/NewEdit")
    public String NewEdit(Model model,
                          HttpServletRequest request,
                          Course course){

        Teacher UserSession = (Teacher) request.getSession().getAttribute("UserSession");
        Course coursetemp = courseMapper.selectById(course.cid);
        if(coursetemp != null){
            model.addAttribute("msg","课程代码已存在，请重新输入！");
            Course coursetemp2 = new Course();
            model.addAttribute("coursetemp",coursetemp2);
            return "NewEdit";
        }
        else{
            course.selected = 0;
            int result =  courseMapper.insert(course);
            return "redirect:/Tmain";
        }
    }

    @GetMapping("/edit/{cid}")
    public String Edit(Model model,
                       HttpServletRequest request,
                       @PathVariable int cid){
        Course coursetemp = courseMapper.selectById(cid);
        model.addAttribute("coursetemp",coursetemp);
        return "edit";
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
