package com.xkkj.code.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_account")
@ApiModel(value = "UserAccount对象", description = "")
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;


    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "状态( 0:禁用, 1:正常 )")
    private Integer accountStatus;

    @ApiModelProperty(value = "账户类型( 1:普通用户, 2:运营人员 )")
    private Integer accountType;

    @ApiModelProperty(value = "是否完善个人信息( 0:未完善, 1:已完善 )")
    private Integer fillUserinfo;

    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "创建时间", example = "2019-01-01 8:00:00")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
