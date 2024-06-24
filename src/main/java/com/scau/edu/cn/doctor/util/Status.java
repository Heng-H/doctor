package com.scau.edu.cn.doctor.util;


import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum Status {

    // 成功统一返回200
    SUCCESS(200, ""),





    USER_LOGIN_NOT_EXIST(500, "用户不存在"),
    USER_LOGIN_PASSWORD_ERROR(500, "密码错误"),
    USER_REGISTER_FAILED(500, "用户注册失败"),
    USER_REGISTER_ALREADY_EXIST(500, "用户已存在"),
    //修改密码失败

  /*  //验证码正确
    USER_CAPTCHA_SUCCESS(6, "验证码正确"),*/
    //验证码错误

    USER_CAPTCHA_ERROR(500, "验证码错误"),
    //
    USER_CAPTCHA_SEND_TOO_FAST(500, "验证码发送过快，请稍后再试"),
    //验证码不存在
    USER_CAPTCHA_NOT_EXIST(500, "验证码不存在"),
    //请先注册
    USER_UPDATE_PASSWORD_NOT_EXIST(500,"用户不存在，请先注册"),
    /*  //修改密码成功
       USER_UPDATE_PASSWORD_SUCCESS(11,"修改密码成功"),*/
    //修改密码失败
    USER_UPDATE_PASSWORD_FAILED(500,"修改密码失败"),

    //重新登录
    USER_LOGIN_RELOGIN(100, "重新登录"),


    HOSPITAL_FIND_NOT_EXIST(500, "医院不存在"),


    ORDER_FIND_NOT_EXIST(500, "订单不存在"),
    ORDER_CREATE_FAILED(500, "今日已预约，订单创建失败"),
    ORDER_UPDATE_FAILED(500, "订单更新失败"),

    SETMEAL_FIND_NOT_EXIST(500, "套餐不存在"),

    CIREPORT_FIND_NOT_EXIST(500, "诊断报告不存在"),

    //新增总检结论项失败
    CIREPORT_SAVE_CONCLUSION_FAILED(500, "新增总检结论项失败"),

    //修改总检结论项失败
    CIREPORT_UPDATE_CONCLUSION_FAILED(500, "修改总检结论项失败"),

    //总检结论项不存在
    CIREPORT_UPDATE_CONCLUSION_NOT_EXIST(500, "总检结论项不存在"),


    //保存检查项失败
    CIREPORT_SAVE_FAILED(500, "保存检查项失败"),
    //体检报告归档失败
    CIREPORT_ARCHIVE_FAILED(500, "体检报告归档失败"),

    //套餐为空
    SETMEAL_IS_EMPTY(500, "套餐为空"),
    ;


    private final Integer code;
    private final String message;
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    //返回静态

}
