package com.lzp.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class NewCourseVO {
    public String cname;
    public String credit;
    public String teacher;
    public int Slimit;
    public String introduce;
}