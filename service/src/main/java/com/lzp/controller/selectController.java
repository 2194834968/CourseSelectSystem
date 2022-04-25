package com.lzp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzp.Mapper.CourseMapper;
import com.lzp.Mapper.SCMapper;
import com.lzp.Mapper.StudentMapper;
import com.lzp.common.result.Result;
import com.lzp.model.Course;
import com.lzp.model.Student;
import com.lzp.model.sc;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.lzp.common.result.ResultCodeEnum.COURSE_SELECTED;
import static com.lzp.common.result.ResultCodeEnum.COURSE_UN_SELECTED;

@RestController
@RequestMapping("/course")
public class selectController {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SCMapper scMapper;

    @ApiOperation(value = "添加userid用户所选的cid课程")
    @GetMapping("/{userid}/select/{cid}")
    public Result select(@PathVariable int userid,
                         @PathVariable int cid){

        QueryWrapper<sc> scWrapper = new QueryWrapper<>();
        scWrapper.eq("sid",userid);
        List<sc> usersc = scMapper.selectList(scWrapper);
        for(sc i : usersc){
            if(i.cid == cid){
                return Result.fail(COURSE_SELECTED);
            }
        }
        //更新选课表
        sc scTemp = new sc(userid,cid);
        int result = scMapper.insert(scTemp);
        //更新课程表对应课程的选课人数
        Course courseTemp = courseMapper.selectById(cid);
        courseTemp.selected = courseTemp.selected + 1;
        result =  courseMapper.updateById(courseTemp);

        return Result.ok();
    }

    @ApiOperation(value = "删除userid用户所选的cid课程")
    @GetMapping("/{userid}/deselect/{cid}")
    public Result deselect(@PathVariable int userid,
                           @PathVariable int cid){
        //查询该用户已选课表
        QueryWrapper<sc> scWrapper = new QueryWrapper<>();
        scWrapper.eq("sid",userid);
        List<sc> usersc = scMapper.selectList(scWrapper);
        for(sc i : usersc){
            if(i.cid == cid){
                //更新选课表
                scWrapper.eq("cid",cid);
                int result = scMapper.delete(scWrapper);
                //更新课程表对应课程的选课人数
                Course courseTemp = courseMapper.selectById(cid);
                courseTemp.selected = courseTemp.selected - 1;
                result =  courseMapper.updateById(courseTemp);

                return Result.ok();
            }
        }
        return Result.fail(COURSE_UN_SELECTED);
    }

}
