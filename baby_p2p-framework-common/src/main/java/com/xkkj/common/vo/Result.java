package com.xkkj.common.vo;

import com.xkkj.common.constants.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一返回类
 * @param <T>
 */
@Data
@ApiModel(value = "全局统一返回结果2,泛型返回类")
public class Result<T> {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String msg;
    @ApiModelProperty(value = "返回数据")
    private T data;

    private Result() {
    }

    public static Result ok() {
        Result r = new Result();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMsg(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    public static Result error() {
        Result r = new Result();
        r.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        r.setMsg(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return r;
    }

    public static Result setResultBody(ResultCodeEnum resultCodeEnum) {
        Result r = new Result();
        r.setSuccess(resultCodeEnum.getSuccess());
        r.setCode(resultCodeEnum.getCode());
        r.setMsg(resultCodeEnum.getMessage());
        return r;
    }

    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result message(String message) {
        this.setMsg(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result result(T data) {
        this.setData(data);
        return this;
    }

}
