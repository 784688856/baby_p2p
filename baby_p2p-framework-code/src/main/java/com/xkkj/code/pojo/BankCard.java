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
@TableName("t_bank_card")
@ApiModel(value="BankCard对象", description="")
public class BankCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "开户人姓名")
    private String realname;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "银行卡号")
    private String cardNumber;

    @ApiModelProperty(value = "支行名称")
    private String branchName;

    @ApiModelProperty(value = "可用余额")
    private Long balance;

    @ApiModelProperty(value = "创建时间",example = "2019-01-01 8:00:00")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
