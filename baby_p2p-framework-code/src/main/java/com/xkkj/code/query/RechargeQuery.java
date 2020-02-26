package com.xkkj.code.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * CREATE BY YB ON 2020/2/23 11:36
 */
@Data
public class RechargeQuery implements Serializable {
    @ApiModelProperty(value = "开始日期", example = "2019-01-01 8:00:00")
    private String beginDate;

    @ApiModelProperty(value = "结束日期", example = "2019-01-01 8:00:00")
    private String endDate;

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "交易号")
    private String tradeNo;

    @ApiModelProperty(value = "审核状态")
    private Integer state;

    @ApiModelProperty(value = "当前页码")
    private Integer currentPage;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize;
}
