package com.lzp.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class sc {
    @TableId
    public int scid;
    public int sid;
    public int cid;

    public sc(int sid,int cid){
        this.setSid(sid);
        this.setCid(cid);
    }
}
