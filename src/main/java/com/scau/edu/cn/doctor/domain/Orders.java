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
 * @TableName orders
 */
@TableName(value ="orders")
@Data
public class Orders implements Serializable {
    /**
     * 订单编号
     */
    @TableId(value = "orderId", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 预约日期
     */
    @TableField(value = "orderDate")
    private Date orderDate;

    /**
     * 客户编号
     */
    @TableField(value = "userId")
    private String userId;

    /**
     * 所属医院编号
     */
    @TableField(value = "hpId")
    private Integer hpId;

    /**
     * 所属套餐编号
     */
    @TableField(value = "smId")
    private Integer smId;

    /**
     * 订单状态（1：未归档；2：已归档）
     */
    @TableField(value = "state")
    private Integer state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}