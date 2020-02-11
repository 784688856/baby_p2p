package com.xkkj.code.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("t_user_wallet")
@ApiModel(value="UserWallet对象", description="")
public class UserWallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户id")
    @TableId(value = "account_id", type = IdType.ID_WORKER_STR)
    private String accountId;

    @ApiModelProperty(value = "可用金额(单位：分)")
    private Long availableAmount;

    @ApiModelProperty(value = "冻结金额(单位：分)")
    private Long freezeAmount;

    @ApiModelProperty(value = "待收利息(单位：分)")
    private Long interestPending;

    @ApiModelProperty(value = "待收本金(单位：分)")
    private Long principalPending;

    @ApiModelProperty(value = "待还金额(单位：分)")
    private Long repaidAmount;

    @ApiModelProperty(value = "信用得分")
    private Integer creditScore;

    @ApiModelProperty(value = "授信额度(单位：分)")
    private Long creditLine;

    @ApiModelProperty(value = "剩余授信额度(单位：分)")
    private Long residualCreditLine;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
