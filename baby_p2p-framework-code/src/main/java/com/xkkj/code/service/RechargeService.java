package com.xkkj.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 用户充值列表查询 --xh
     * @param pageParam
     * @param rechargeQuery
     * @return
     */
    IPage<Recharge> query(Page<Recharge> pageParam, RechargeQuery rechargeQuery);
}
