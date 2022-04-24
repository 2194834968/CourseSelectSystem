package com.lzp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzp.Mapper.StudentMapper;
import com.lzp.common.result.Result;
import com.lzp.model.Course;
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

    @PostMapping("/register")
    public Result register(Student student){

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("max(userid)");
        Long newUserid = Long.parseLong(studentMapper.selectOne(queryWrapper).userid)+1;
        Student studentTemp = new Student(newUserid.toString(), student.username,student.password);

        int result = studentMapper.insert(studentTemp);
        return Result.ok();
    }
}
