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
@TableName("t_recharge")
@ApiModel(value="Recharge对象", description="")
public class Recharge implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "充值账户id")
    private String userId;

    @ApiModelProperty(value = "平台账号")
    private String bankCardName;

    @ApiModelProperty(value = "审核人")
    private String applyName;


    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "银行卡id")
    private String bankCardId;

    @ApiModelProperty(value = "交易号")
    private String tradeNo;

    @ApiModelProperty(value = "充值金额(单位：分)")
    private Long amount;

    @ApiModelProperty(value = "充值日期")
    private String rechargeTime;

    @ApiModelProperty(value = "审核状态( 0:审核拒绝  1:审核中  2:审核通过 )")
    private Integer state;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间",example = "2019-01-01 8:00:00")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
