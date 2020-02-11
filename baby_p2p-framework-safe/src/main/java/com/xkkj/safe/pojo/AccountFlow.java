package com.xkkj.safe.pojo;

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
@TableName("t_account_flow")
@ApiModel(value="AccountFlow对象", description="")
public class AccountFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "账户id")
    private String accountId;

    @ApiModelProperty(value = "金额(单位：分)")
    private Long amount;

    @ApiModelProperty(value = "资金流水类型")
    private Integer flowType;

    @ApiModelProperty(value = "(变化后)可用金额(单位：分)")
    private Long availableAmount;

    @ApiModelProperty(value = "(变化后)冻结金额(单位：分)")
    private Long freezeAmount;

    @ApiModelProperty(value = "流水说明")
    private String remark;

    @ApiModelProperty(value = "创建日期")
    private Date createTime;


}
