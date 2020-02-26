package com.xkkj.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xkkj.code.pojo.Recharge;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xkkj.code.query.RechargeQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
public interface RechargeService extends IService<Recharge> {

    // TODO yb专区
    void add(Recharge recharge);

    IPage<Recharge> rechargePage(RechargeQuery rechargeQuery);
    // TODO yb专区


}
