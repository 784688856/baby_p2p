package com.xkkj.code.service;

import com.xkkj.code.pojo.Withdraw;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
public interface WithdrawService extends IService<Withdraw> {
    // TODO yb地盘
    void add(Withdraw withdraw);
    // TODO yb地盘
}
