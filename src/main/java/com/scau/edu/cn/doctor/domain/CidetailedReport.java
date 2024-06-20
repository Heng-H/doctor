package com.scau.edu.cn.doctor.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName cidetailed_report
 */
@TableName(value ="cidetailed_report")
@Data
public class CidetailedReport implements Serializable {
    /**
     * 检查项明细报告主键
     */
    @TableId(value = "cidrId", type = IdType.AUTO)
    private Integer cidrId;

    /**
     * 检查项明细名称
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
     * 检查项明细类型（1：数值范围验证型；2：数值相等验证型；3：无需验证型；4：描述型；5：其它）
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 检查项目明细值
     */
    @TableField(value = "value")
    private String value;

    /**
     * 此项是否异常（0：无异常；1：异常）
     */
    @TableField(value = "isError")
    private Integer isError;

    /**
     * 所属检查项报告编号
     */
    @TableField(value = "ciId")
    private Integer ciId;

    /**
     * 所属预约编号
     */
    @TableField(value = "orderId")
    private Integer orderId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}