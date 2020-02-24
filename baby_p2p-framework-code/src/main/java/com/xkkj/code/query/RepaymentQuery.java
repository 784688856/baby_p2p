package com.xkkj.code.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RepaymentQuery implements Serializable {
    @ApiModelProperty(value = "开始日期",example = "2019-01-01 8:00:00")
    private String beginDate;

    @ApiModelProperty(value = "结束日期",example = "2019-01-01 8:00:00")
    private String endDate;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "还款状态码")
    private Integer state;

    @ApiModelProperty(value="当前页码")
    private Integer currentPage;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize;
}
