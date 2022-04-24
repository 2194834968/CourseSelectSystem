package com.lzp.common.result;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(400, "失败"),

    //课程编辑系统
    COURSENAME_REPEAT( 401, "课名重复"),
    EDIT_FAIL(402,"找不到对应的课程，无法提交修改"),
    DELETE_FAIL(403,"删除失败")
    ;

    private Integer code;
    private String message;

    public String getMsg() {
        return message;
    }

    public int getStatusCode() {
        return code;
    }

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
