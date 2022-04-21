package com.lzp.controller;

import com.lzp.Mapper.CourseMapper;
import com.lzp.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TmainController {

    @Autowired
    CourseMapper courseMapper;

    @GetMapping(value = {"/Tmain"})
    public String main(Model model,
                       HttpServletRequest request){

        //显示可选课表
        List<Course> course = courseMapper.selectList(null);
        model.addAttribute("course",course);

        return "Tmain";
    }
}
