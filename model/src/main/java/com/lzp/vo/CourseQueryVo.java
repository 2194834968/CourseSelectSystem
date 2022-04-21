package com.lzp.vo;

import lombok.Data;

@Data
public class CourseQueryVo {
    //VO类用于封装数据库查询条件
    public int cid;
    public String cname;
    public String teacher;
}
