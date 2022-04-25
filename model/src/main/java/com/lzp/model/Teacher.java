package com.lzp.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Teacher {
    @TableId
    public int userid;
    public String username;
    public String password;

    public Teacher(int userid, String username, String password){
        this.setUserid(userid);
        this.setUsername(username);
        this.setPassword(password);
        return ;
    }
}
