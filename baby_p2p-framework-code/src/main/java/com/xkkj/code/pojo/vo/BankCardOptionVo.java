package com.xkkj.code.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 银行卡下拉选项
 * @description: BankCardOptionVo
 * @author: xuhao
 * @time: 2020/2/29 13:38
 */
@Data
public class BankCardOptionVo implements Serializable {
    @ApiModelProperty(value = "下拉框")
    private String value;
    @ApiModelProperty(value = "下拉框")
    private String label;
}
