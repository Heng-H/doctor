package com.scau.edu.cn.doctor.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName set_meal
 */
@TableName(value ="set_meal")
@Data
public class SetMeal implements Serializable {
    /**
     * 套餐编号
     */
    @TableId(value = "smId", type = IdType.AUTO)
    private Integer smId;

    /**
     * 套餐名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 套餐类型（1：男士餐套；0：女士套餐）
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 套餐价格
     */
    @TableField(value = "price")
    private Integer price;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}