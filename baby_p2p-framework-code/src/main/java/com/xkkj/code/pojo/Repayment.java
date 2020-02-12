package com.xkkj.code.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_repayment")
@ApiModel(value="Repayment对象", description="")
public class Repayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "借款id")
    private String borrowId;

    @ApiModelProperty(value = "借款人id")
    private String borrowUserId;

    @ApiModelProperty(value = "借款标题")
    private String borrowTitle;

    @ApiModelProperty(value = "截止日期")
    private Date deadline;

    @ApiModelProperty(value = "还款日期")
    private Date repaymentTime;

    @ApiModelProperty(value = "本期还款总金额(单位：分)")
    private Long totalAmount;

    @ApiModelProperty(value = "本期还款本金(单位：分)")
    private Long principal;

    @ApiModelProperty(value = "本期还款总利息(单位：分)")
    private Long interest;

    @ApiModelProperty(value = "还款期数(第几期)")
    private Integer period;

    @ApiModelProperty(value = "还款状态")
    private Integer state;

    @ApiModelProperty(value = "借款类型")
    private Integer borrowType;

    @ApiModelProperty(value = "还款方式")
    private Integer repaymentType;

    @ApiModelProperty(value = "创建时间",example = "2019-01-01 8:00:00")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
