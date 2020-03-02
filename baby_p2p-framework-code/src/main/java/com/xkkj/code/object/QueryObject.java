package com.xkkj.code.object;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "查询对象")
@Data
public class QueryObject {

    @ApiModelProperty(value = "开始日期（yyyy-MM-dd HH:mm:ss）")
    private String beginDate;

    @ApiModelProperty(value = "结束日期（yyyy-MM-dd HH:mm:ss）")
    private String endDate;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "借款状态”码列表，如：1,2,3")
    private List borrowStates;

    @ApiModelProperty(value = "当前页码")
    private Integer currentPage;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize;
}
