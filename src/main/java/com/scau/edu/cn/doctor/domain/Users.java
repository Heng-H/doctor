package com.scau.edu.cn.doctor.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName users
 */
@TableName(value ="users")
@Data
public class Users implements Serializable {
    /**
     * 用户编号（手机号码）
     */
    @TableId(value = "userId")
    private String userId;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 真实姓名
     */
    @TableField(value = "realName")
    private String realName;

    /**
     * 用户性别（1：男；0女）
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 身份证号
     */
    @TableField(value = "identityCard")
    private String identityCard;

    /**
     * 出生日期
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 用户类型（1：普通用户；2：东软内部员工；3：其他）
     */
    @TableField(value = "userType")
    private Integer userType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}