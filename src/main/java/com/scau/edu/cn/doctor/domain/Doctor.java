package com.scau.edu.cn.doctor.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName doctor
 */
@TableName(value ="doctor")
@Data
public class Doctor implements Serializable {
    /**
     * 医生编号
     */
    @TableId(value = "docId")
    private String docId;

    /**
     * 医生编码
     */
    @TableField(value = "docCode")
    private String docCode;

    /**
     * 真实姓名
     */
    @TableField(value = "realName")
    private String realName;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 所属科室（1：检验科；2：内科；3：外科）
     */
    @TableField(value = "deptno")
    private Integer deptno;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}