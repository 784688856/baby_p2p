package com.xkkj.common.exception;

import com.xkkj.common.constants.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: YanXueException
 * @author: xuhao
 * @time: 2020/1/6 10:31
 */
@Data
@ApiModel(value = "全局异常")
public class YanXueException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;
    /**
     * 接受状态码和消息
     * @param code
     * @param message
     */
    public YanXueException(Integer code, String message) {
        super(message);
        this.code=code;
    }
    /**
     * 接收枚举类型
     * @param resultCodeEnum
     */
    public YanXueException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "YanXueException{" +
                "message=" + this.getMessage() +
                ", code=" + code +
                '}';
    }
}

