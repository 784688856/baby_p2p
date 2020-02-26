package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xkkj.code.mapper.LoginLogMapper;
import com.xkkj.code.mapper.MailVerifyMapper;
import com.xkkj.code.pojo.LoginLog;
import com.xkkj.code.pojo.MailVerify;
import com.xkkj.code.query.MailQuery;
import com.xkkj.code.service.MailVerifyService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * CREATE BY YB ON 2020/2/24 9:18
 */
@Service
public class MailVerityServiceImpl extends ServiceImpl<MailVerifyMapper, MailVerify> implements MailVerifyService {
    // 新增
    @Override
    public int add(MailVerify mailVerify) {
        baseMapper.insert(mailVerify);
        // mybatispuls直接可以获取主键,但是需要将pojo主键改成类型auto
        // @TableId(value = "id", type = IdType.AUTO)
        // private int id;
        int id = mailVerify.getId();
        return id;
    }

    // 检查验证码
    @Override
    public Boolean checkEmail(MailQuery mailQuery) {
        MailVerify mailVerify = getById(mailQuery.getId());
        System.out.println(mailVerify);
        if (mailVerify != null) {
            String randomCode = mailVerify.getRandomCode();
            String code = mailQuery.getCode();
            if (code.equals(randomCode)) {
                // 验证码匹配
                return true;
            }
        }
        return false;
    }
}
