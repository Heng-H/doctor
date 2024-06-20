package com.scau.edu.cn.doctor.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName cireport
 */
@TableName(value ="cireport")
@Data
public class Cireport implements Serializable {
    /**
     * 检查项报告主键
     */
    @TableId(value = "cirId", type = IdType.AUTO)
    private Integer cirId;

    /**
     * 检查项编号
     */
    @TableField(value = "ciId")
    private Integer ciId;

    /**
     * 检查项名称
     */
    @TableField(value = "ciName")
    private String ciName;

    /**
     * 所属预约编号
     */
    @TableField(value = "orderId")
    private Integer orderId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}