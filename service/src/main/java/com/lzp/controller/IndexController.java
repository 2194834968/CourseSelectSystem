package com.lzp.controller;

import com.lzp.Mapper.StudentMapper;
import com.lzp.Mapper.TeacherMapper;
import com.lzp.common.result.Result;
import com.lzp.model.Student;
import com.lzp.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @PostMapping("/login")
    public Result login(Student student){

        Student studentTemp = studentMapper.selectById(student.userid);

        if(studentTemp.password.equals(student.password)){
            student = studentTemp;
            return Result.ok(student);
        }else{
            return Result.fail();
        }
    }

    @PostMapping("/Tlogin")
    public Result Tlogin(Teacher teacher){

        Teacher teachertemp = teacherMapper.selectById(teacher.userid);

        if(teachertemp.password.equals(teacher.password)){
            return Result.ok(teachertemp);
        }else{
            return Result.fail();
        }
    }
}
