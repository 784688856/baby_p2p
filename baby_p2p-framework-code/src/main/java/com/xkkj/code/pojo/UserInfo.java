package com.xkkj.code.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("t_user_info")
@ApiModel(value="UserInfo对象", description="")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户id")
    @TableId(value = "account_id", type = IdType.ID_WORKER_STR)
    private String accountId;

    @ApiModelProperty(value = "肖像图片")
    private String avatar;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "身份证号码")
    private String idCardNumber;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "收入等级id")
    private Integer incomeLevelId;

    @ApiModelProperty(value = "婚姻状况id")
    private Integer marriageId;

    @ApiModelProperty(value = "教育背景id")
    private Integer eduBackgroundId;

    @ApiModelProperty(value = "住房情况id")
    private Integer houseConditionId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
