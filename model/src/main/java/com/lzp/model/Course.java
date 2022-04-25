package com.lzp.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Course {
    @TableId
    public int cid;
    public String cname;
    public String credit;
    public int tid;
    public String teacher;
    public int Slimit;
    public int selected;
    public String introduce;
}
