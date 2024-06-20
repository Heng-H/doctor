package com.scau.edu.cn.doctor.util;


import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum Status {

    // 成功统一返回1
    SUCCESS(1, ""),



    USER_LOGIN_NOT_EXIST(2, "用户不存在"),
    USER_LOGIN_PASSWORD_ERROR(3, "密码错误"),
    USER_REGISTER_FAILED(4, "用户注册失败"),
    USER_REGISTER_ALREADY_EXIST(5, "用户已存在"),
  /*  //验证码正确
    USER_CAPTCHA_SUCCESS(6, "验证码正确"),*/
    //验证码错误

    USER_CAPTCHA_ERROR(7, "验证码错误"),
    //
    USER_CAPTCHA_SEND_TOO_FAST(8, "验证码发送过快，请稍后再试"),
//验证码不存在
    USER_CAPTCHA_NOT_EXIST(9, "验证码不存在"),
    //请先注册
    USER_UPDATE_PASSWORD_NOT_EXIST(10,"用户不存在，请先注册"),
 /*  //修改密码成功
    USER_UPDATE_PASSWORD_SUCCESS(11,"修改密码成功"),*/
    //修改密码失败
    USER_UPDATE_PASSWORD_FAILED(12,"修改密码失败"),

    //重新登录
    USER_LOGIN_RELOGIN(100, "重新登录"),


    HOSPITAL_FIND_NOT_EXIST(2, "医院不存在"),


    ORDER_FIND_NOT_EXIST(2, "订单不存在"),
    ORDER_CREATE_FAILED(3, "今日已预约，订单创建失败"),
    ORDER_UPDATE_FAILED(4, "订单更新失败"),

    SETMEAL_FIND_NOT_EXIST(2, "套餐不存在"),

    CIREPORT_FIND_NOT_EXIST(2, "诊断报告不存在");



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
