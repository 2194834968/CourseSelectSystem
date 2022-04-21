package com.lzp.controller;

import com.lzp.Mapper.StudentMapper;
import com.lzp.Mapper.TeacherMapper;
import com.lzp.common.result.Result;
import com.lzp.model.Student;
import com.lzp.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @GetMapping(value = {"/index","/login","/"})
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public Result login(Student student,
                        HttpSession session,
                        Model model,
                        HttpServletResponse response){

        Student studentTemp = studentMapper.selectById(student.userid);

        if(studentTemp.password.equals(student.password)){
            student = studentTemp;
            session.setAttribute("UserSession", student);

            return Result.ok();
        }else{
            model.addAttribute("msg","账号或密码错误！");
            return Result.fail();
        }
    }

    @GetMapping("/Tlogin")
    public String Tlogin(){
        return "Tlogin";
    }

    @PostMapping("/Tlogin")
    public Result Tlogin(Teacher teacher,
                                 HttpSession session,
                                 Model model,
                                 HttpServletResponse response){

        Teacher teachertemp = teacherMapper.selectById(teacher.userid);

        if(teachertemp.password.equals(teacher.password)){
            teacher = teachertemp;
            session.setAttribute("UserSession", teacher);

            return Result.ok();
        }else{
            model.addAttribute("msg","账号或密码错误！");
            return Result.fail();
        }
    }
}
