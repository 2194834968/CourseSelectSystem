package com.lzp.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Student {
    @TableId(value = "userid", type = IdType.AUTO)
    public int userid;
    public String username;
    public String password;

    public Student(String username, String password){
        this.setUserid(userid);
        this.setUsername(username);
        this.setPassword(password);
    }
}
