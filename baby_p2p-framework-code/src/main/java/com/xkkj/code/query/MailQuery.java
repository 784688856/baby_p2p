package com.xkkj.code.query;

import com.xkkj.code.pojo.MailVerify;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 验证码
 * CREATE BY YB ON 2020/2/24 8:30
 */
@Data
public class MailQuery extends MailVerify implements Serializable{
    @ApiModelProperty(value = "绑定的新邮箱")
    private String newEmail;
    @ApiModelProperty(value = "用户输入的四位数")
    private String code;

}
