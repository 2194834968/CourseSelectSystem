package com.lzp.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Student {
    @TableId
    public String userid;
    public String username;
    public String password;

    public Student(String userid, String username, String password){
        this.setUserid(userid);
        this.setUsername(username);
        this.setPassword(password);
        return ;
    }
}