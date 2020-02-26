package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xkkj.code.pojo.BankCard;
import com.xkkj.code.mapper.BankCardMapper;
import com.xkkj.code.service.BankCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Service
public class BankCardServiceImpl extends ServiceImpl<BankCardMapper, BankCard> implements BankCardService {

    // TODO yb 专区

    @Override
    public BankCard getInfoByUserId(String uid) {
        QueryWrapper<BankCard> wrapper = new QueryWrapper();
        wrapper.eq("user_id",uid);
        BankCard one = getOne(wrapper);
        return one;
    }
    // TODO yb 专区

}
