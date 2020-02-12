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
@TableName("t_withdraw")
@ApiModel(value="Withdraw对象", description="")
public class Withdraw implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "提现账户id")
    private String userId;

    @ApiModelProperty(value = "提现金额(单位：分)")
    private Long amount;

    @ApiModelProperty(value = "手续费(单位：分)")
    private Long fee;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "开户人姓名")
    private String realname;

    @ApiModelProperty(value = "银行卡号")
    private String cardNumber;

    @ApiModelProperty(value = "支行名称")
    private String branchName;

    @ApiModelProperty(value = "创建时间",example = "2019-01-01 8:00:00")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
