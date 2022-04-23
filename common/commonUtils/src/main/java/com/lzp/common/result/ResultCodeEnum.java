package com.lzp.common.result;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),

    //课程编辑系统
    COURSENAME_REPEAT( 202, "课名重复"),
    SERVICE_ERROR(203, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    DATA_UPDATE_ERROR(205, "数据版本异常"),

    LOGIN_AUTH(208, "未登陆"),
    PERMISSION(209, "没有权限"),

    LOGIN_AURH(214, "需要登录"),
    LOGIN_ACL(215, "没有权限"),

    FETCH_USERINFO_ERROR( 219, "获取用户信息失败"),
    //LOGIN_ERROR( 23005, "登录失败"),
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
