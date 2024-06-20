package com.scau.edu.cn.doctor.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName check_item_detailed
 */
@TableName(value ="check_item_detailed")
@Data
public class CheckItemDetailed implements Serializable {
    /**
     * 检查项明细编号
     */
    @TableId(value = "cdId", type = IdType.AUTO)
    private Integer cdId;

    /**
     * 检查项细明名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 检查项明细单位
     */
    @TableField(value = "unit")
    private String unit;

    /**
     * 检查项细明正常值范围中的最小值
     */
    @TableField(value = "minrange")
    private Double minrange;

    /**
     * 检查项细明正常值范围中的最大值
     */
    @TableField(value = "maxrange")
    private Double maxrange;

    /**
     * 检查项细明正常值（非数字型）
     */
    @TableField(value = "normalValue")
    private String normalValue;

    /**
     * 检查项验证范围说明文字
     */
    @TableField(value = "normalValueString")
    private String normalValueString;

    /**
     * 检查项明细类型（1：数值围范验证型；2：数值相等验证型；3：无需验证型；4：描述型；5：其它）
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 所属检查项编号
     */
    @TableField(value = "ciId")
    private Integer ciId;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}