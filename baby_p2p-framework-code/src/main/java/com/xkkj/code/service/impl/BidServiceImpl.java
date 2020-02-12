package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.Bid;
import com.xkkj.code.mapper.BidMapper;
import com.xkkj.code.query.BidQuery;
import com.xkkj.code.service.BidService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Service
public class BidServiceImpl extends ServiceImpl<BidMapper, Bid> implements BidService {
    @Autowired
    BidMapper bidMapper;

    @Override
    public IPage<Bid> page(BidQuery bidQuery) {
        Page<Bid> page = new Page<>(bidQuery.getCurrentPage(),bidQuery.getPageSize());
        //条件
        QueryWrapper<Bid> queryWrapper = new QueryWrapper<>();
        // 根据时间排序
        queryWrapper.orderByDesc("create_time");
        //开始时间
        String beginDate=bidQuery.getBeginDate();
        //结束时间
        String endDate=bidQuery.getEndDate();
        //用户id
        String userId=bidQuery.getUserId();
        //借款状态码
        int borrowStates = bidQuery.getBorrowStates();
        if (StringUtils.isNotBlank(beginDate)) {
            queryWrapper.gt("create_time", beginDate);
        }
        if (StringUtils.isNotBlank(beginDate)) {
            queryWrapper.lt("create_time", endDate);
        }
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.like("bid_user_id", userId);
        }
        if (borrowStates!=0){
            queryWrapper.eq("borrow_state",borrowStates);
        }
        IPage<Bid> bidIPage = bidMapper.selectPage(page, queryWrapper);
        return bidIPage;
    }

    @Override
    public List<Bid> getByBorrowId(String borrowId) {
        return bidMapper.getByBorrowId(borrowId);
    }

    @Override
    public void add(String bidUserId, String bidUsername, String borrowId, Integer bidAmount) {
        /*投标
        先用投标人id和借款标id查询有没有同一个投标人在同一个借款标投钱，有酒把此次金额加上，
        没有就新建一个投标数据*/
        /*
        新建投标数据时，先用借款标id查询得到借款标数据再添加
         */

    }
}
