package com.xkkj.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xkkj.code.pojo.MailVerify;
import com.xkkj.code.query.MailQuery;

/**
 * CREATE BY YB ON 2020/2/24 9:17
 */
public interface MailVerifyService extends IService<MailVerify> {
    int add(MailVerify mailVerify);

    Boolean checkEmail(MailQuery mailQuery);
}
