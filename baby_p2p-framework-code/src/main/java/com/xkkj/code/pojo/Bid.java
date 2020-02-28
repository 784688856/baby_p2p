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
@TableName("t_bid")
@ApiModel(value="Bid对象", description="")
public class Bid implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "借款id")
    private String borrowId;

    @ApiModelProperty(value = "借款标题")
    private String borrowTitle;

    @ApiModelProperty(value = "投标金额")
    private Integer bidAmount;

    @ApiModelProperty(value = "投标利息")
    private Integer bidInterest;

    @ApiModelProperty(value = "年化率")
    private Integer yearRate;

    @ApiModelProperty(value = "借款状态")
    private Integer borrowState;

    @ApiModelProperty(value = "投标人id")
    private String bidUserId;

    @ApiModelProperty(value = "投标人用户名")
    private String bidUsername;

    @ApiModelProperty(value = "投标时间")
    private Date bidTime;

    @ApiModelProperty(value = "创建时间",example = "2019-01-01 8:00:00")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
