package com.xkkj.common.exception;

import com.xkkj.common.constants.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: BabyP2pException
 * @author: xuhao
 * @time: 2020/2/11 10:31
 */
@Data
@ApiModel(value = "全局异常")
public class BabyP2pException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;
    /**
     * 接受状态码和消息
     * @param code
     * @param message
     */
    public BabyP2pException(Integer code, String message) {
        super(message);
        this.code=code;
    }
    /**
     * 接收枚举类型
     * @param resultCodeEnum
     */
    public BabyP2pException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "BabyP2pException{" +
                "msg=" + this.getMessage() +
                ", code=" + code +
                '}';
    }
}

