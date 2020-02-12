package com.xkkj.code.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.Bid;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xkkj.code.query.BidQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
public interface BidService extends IService<Bid> {
    //投资明细分页查询
    IPage<Bid> page(BidQuery bidQuery);
    //查询标的投标列表信息
    List<Bid> getByBorrowId(String borrowId);
    //发起投标
    void add(String bidUserId,String bidUsername,String borrowId,Integer bidAmount);
}
