package com.lzp.controller;

import com.lzp.Mapper.StudentMapper;
import com.lzp.Mapper.TeacherMapper;
import com.lzp.common.result.Result;
import com.lzp.model.Student;
import com.lzp.model.Teacher;
import com.lzp.vo.StudentRegisterVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @ApiOperation(value = "学生登陆")
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

    @ApiOperation(value = "教师登陆")
    @PostMapping("/Tlogin")
    public Result Tlogin(Teacher teacher){

        Teacher teachertemp = teacherMapper.selectById(teacher.userid);

        if(teachertemp.password.equals(teacher.password)){
            return Result.ok(teachertemp);
        }else{
            return Result.fail();
        }
    }

    @ApiOperation(value = "学生注册")
    @PostMapping("/register")
    public Result register(StudentRegisterVO studentRegisterVO){

        Student studentTemp = new Student(studentRegisterVO.username,studentRegisterVO.password);
        int result = studentMapper.insert(studentTemp);
        return Result.ok(studentTemp);
    }
}