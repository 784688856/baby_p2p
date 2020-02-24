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
@TableName("t_borrow")
@ApiModel(value="Borrow对象", description="")
public class Borrow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "借款人id")
    private String borrowUserId;

    @ApiModelProperty(value = "借款人用户名")
    private String borrowUsername;

    @ApiModelProperty(value = "借款标题")
    private String title;

    @ApiModelProperty(value = "借款描述")
    private String description;

    @ApiModelProperty(value = "还款类型( 1.等额本息  2.先息后本 )")
    private Integer repaymentType;

    @ApiModelProperty(value = "借款类型( 1.信用贷 2.车贷 3.房贷 )")
    private Integer borrowType;

    @ApiModelProperty(value = "借款状态")
    private Integer borrowState;

    @ApiModelProperty(value = "借款总金额(单位：分)")
    private Long borrowAmount;

    @ApiModelProperty(value = "年化率")
    private Integer yearRate;

    @ApiModelProperty(value = "还款期数")
    private Integer repaymentMonth;

    @ApiModelProperty(value = "已投标数量")
    private Integer bidNum;

    @ApiModelProperty(value = "总回报金额(单位：分)")
    private Long totalInterest;

    @ApiModelProperty(value = "当前已投标金额(单位：分)")
    private Long currentBidAmount;

    @ApiModelProperty(value = "当前已投标利息(单位：分)")
    private Long currentBidInterest;

    @ApiModelProperty(value = "招标截止日期")
    private Date bidDeadline;

    @ApiModelProperty(value = "招标天数")
    private Integer bidDays;

    @ApiModelProperty(value = "申请时间")
    private Date applyTime;

    @ApiModelProperty(value = "发标时间")
    private Date publishTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
