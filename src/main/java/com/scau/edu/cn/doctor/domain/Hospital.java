package com.scau.edu.cn.doctor.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName hospital
 */
@TableName(value ="hospital")
@Data
public class Hospital implements Serializable {
    /**
     * 医院编号
     */
    @TableId(value = "hpId", type = IdType.AUTO)
    private Integer hpId;

    /**
     * 医院名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 医院图片
     */
    @TableField(value = "picture")
    private String picture;

    /**
     * 医院电话
     */
    @TableField(value = "telephone")
    private String telephone;

    /**
     * 医院地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 营业时间
     */
    @TableField(value = "businessHours")
    private String businessHours;

    /**
     * 采血截止时间
     */
    @TableField(value = "deadline")
    private String deadline;

    /**
     * 预约人数规则
     */
    @TableField(value = "rule")
    private String rule;

    /**
     * 医院状态（1：正常；2：其他）
     */
    @TableField(value = "state")
    private Integer state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}