package com.xkkj.common.constants;

import lombok.Getter;

/**
 * 返回码定义枚举类
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(true, 20000,"成功"),
    UNKNOWN_REASON(false, 20001, "未知错误"),
    BAD_SQL_GRAMMAR(false, 21001, "sql语法错误"),
    JSON_PARSE_ERROR(false, 21002, "json解析异常"),
    PARAM_ERROR(false, 21003, "参数不正确"),
    FILE_UPLOAD_ERROR(false, 21004, "文件上传错误"),
    USERLOGIN_INVALID_ERROR(false, 21005,"登录信息过期，请重新登录"),
    USERUNLOGIN_ERROR(false, 21006,"用户未登录，请重新登录"),
    URL_ENCODE_ERROR(false,21007,"URL编码错误"),
    ILLEGAL_CALLBACK_REQUEST_ERROR(false,21008,"非法回调请求错误"),
    FETCH_ACCESSTOKEN_FAILD(false,21009,"获取Token失败"),
    FETCH_USERINFO_ERROR(false,21010,"获取用户信息错误");


    private Boolean success;
    private Integer code;
    private String message;
    private ResultCodeEnum(Boolean success, Integer code, String message)
    {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}