package com.lzp.controller;

import com.lzp.Mapper.StudentMapper;
import com.lzp.common.result.Result;
import com.lzp.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class registerController {

    @Autowired
    StudentMapper studentMapper;

    @GetMapping("/register")
    public  String register(){
        return "register";
    }

    @PostMapping("/register")
    public Result register(Student student, HttpSession session, Model model){

        Student studentNew = studentMapper.selectById(student.userid);
        if (studentNew == null){
            Student studentTemp = new Student(student.userid,
                    student.username,
                    student.password);

            int result = studentMapper.insert(studentTemp);
            //System.out.println(result);
            model.addAttribute("msg","注册成功，请重新登录");
            return Result.ok();
        }
        else{
            model.addAttribute("msg","该账号已存在，请重新输入！");
            return Result.fail();
        }
    }
}
