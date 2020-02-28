package com.xkkj.code.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: AdminBankCardQuery 系统银行卡查询类
 * @author: xuhao
 * @time: 2020/2/11 15:17
 */
@Data
public class AdminBankCardQuery implements Serializable {

    @ApiModelProperty(value = "关键字")
    private String keyword;

    @ApiModelProperty(value = "当前页码")
    private Integer currentPage;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize;
}
