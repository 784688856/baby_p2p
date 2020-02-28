package com.xkkj.code.service.impl;

import com.xkkj.code.pojo.Withdraw;
import com.xkkj.code.mapper.WithdrawMapper;
import com.xkkj.code.service.WithdrawService;
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
public class WithdrawServiceImpl extends ServiceImpl<WithdrawMapper, Withdraw> implements WithdrawService {

    // TODO yb地盘
    @Override
    public void add(Withdraw withdraw) {
        // 0.01的手续费
        Long amount = withdraw.getAmount();
        double fee = (double) amount * 0.01;
        withdraw.setFee((Math.round(fee)));
        save(withdraw);
    }
    // TODO yb地盘
}
