package com.xkkj.code.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: LoginLogQuery
 * @author: xuhao
 * @time: 2020/2/11 15:17
 */
@Data
public class LoginLogQuery implements Serializable {

    @ApiModelProperty(value = "开始日期",example = "2019-01-01 8:00:00")
   private String beginDate;

    @ApiModelProperty(value = "结束日期",example = "2019-01-01 8:00:00")
    private String endDate;

    @ApiModelProperty(value = "登录结果标识")
    private Integer loginResult;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "账户类型标识")
    private Integer accountType;

    @ApiModelProperty(value = "当前页码")
    private Integer currentPage;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize;
}
