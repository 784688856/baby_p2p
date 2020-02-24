package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xkkj.code.mapper.RechargeMapper;
import com.xkkj.code.pojo.Recharge;
import com.xkkj.code.query.RechargeQuery;
import com.xkkj.code.service.RechargeService;
import org.apache.commons.lang3.StringUtils;
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
public class RechargeServiceImpl extends ServiceImpl<RechargeMapper, Recharge> implements RechargeService {

    @Override
    public IPage<Recharge> query(Page<Recharge> pageParam, RechargeQuery rechargeQuery) {
        // 条件
        QueryWrapper<Recharge> queryWrapper = new QueryWrapper<>();
        // 根据时间排序
        queryWrapper.orderByDesc("create_time");
        //开始时间
        String beginDate=rechargeQuery.getBeginDate();
        //结束时间
        String endDate=rechargeQuery.getEndDate();
        //用户id
        //String userId=rechargeQuery.getUserId();
        //交易号
        //String tradeNo=rechargeQuery.getTradeNo();
        //充值审核状态标识
        Integer state=rechargeQuery.getState();
        if (StringUtils.isNotBlank(beginDate)) {
            queryWrapper.gt("recharge_time", beginDate);
        }
        if (StringUtils.isNotBlank(beginDate)) {
            queryWrapper.lt("recharge_time", endDate);
        }
        if (state==0||state==1||state==2) {
            queryWrapper.eq("state", state);
        }
        IPage<Recharge> rechargeIPage = baseMapper.selectPage(pageParam, queryWrapper);
        return rechargeIPage;
    }
}
