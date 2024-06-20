package com.scau.edu.cn.doctor.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName set_meal_detailed
 */
@TableName(value ="set_meal_detailed")
@Data
public class SetMealDetailed implements Serializable {
    /**
     * 套餐明细编号（无意义主键）
     */
    @TableId(value = "sdId", type = IdType.AUTO)
    private Integer sdId;

    /**
     * 套餐编号
     */
    @TableField(value = "smId")
    private Integer smId;

    /**
     * 检查项编号
     */
    @TableField(value = "ciId")
    private Integer ciId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}