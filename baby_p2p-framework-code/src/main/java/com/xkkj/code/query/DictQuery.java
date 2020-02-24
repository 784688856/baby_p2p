package com.xkkj.code.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典组查询对象
 * @description: DictQuery
 * @author: xuhao
 * @time: 2020/2/13 21:07
 */
@Data
public class DictQuery {
    @ApiModelProperty(value = "关键字")
    private String keyword;

    @ApiModelProperty(value = "数据字典项父id")
    private Long parented;

    @ApiModelProperty(value = "当前页码")
    private Integer currentPage;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize;
}
