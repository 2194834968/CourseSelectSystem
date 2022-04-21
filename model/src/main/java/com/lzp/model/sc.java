package com.lzp.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class sc {
    @TableId
    public String sid;
    public int cid1;

    public sc(String sid,int cid){
        this.setSid(sid);
        this.setCid1(cid);
    }
}
