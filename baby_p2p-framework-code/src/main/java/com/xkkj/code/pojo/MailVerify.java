package com.xkkj.code.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * CREATE BY YB ON 2020/2/24 9:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_mailverify")
@ApiModel(value = "四位数验证类对象", description = "")
public class MailVerify implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @ApiModelProperty(value = "用户编号")
    private String userId;
    @ApiModelProperty(value = "验证码最后期限")
    private Date deadline;
    @ApiModelProperty(value = "随机数")
    private String randomCode;
}
