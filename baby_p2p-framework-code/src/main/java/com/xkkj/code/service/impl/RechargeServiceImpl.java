package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.AccountFlow;
import com.xkkj.code.pojo.Recharge;
import com.xkkj.code.mapper.RechargeMapper;
import com.xkkj.code.query.RechargeQuery;
import com.xkkj.code.service.RechargeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xkkj.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
@Service
public class RechargeServiceImpl extends ServiceImpl<RechargeMapper, Recharge> implements RechargeService {

    @Autowired
    private IdWorker idWorker;

    // TODO yb专区
    @Override
    public void add(Recharge recharge) {
        // 交易号
        String tradeNo = idWorker.nextId() + "";
        // 充值时间
        long data = System.currentTimeMillis();
        Date date = new Date(data);
        recharge.setRechargeTime(date);
        recharge.setTradeNo(tradeNo);
        // 0:审核拒绝，1：审核中，2：审核通过
        recharge.setState(1);
        save(recharge);

    }

    // 分页查询充值信息
    @Override
    public IPage<Recharge> rechargePage(RechargeQuery rechargeQuery) {
        // 获取查询条件
        String userId = rechargeQuery.getUserId();
        Integer state = rechargeQuery.getState();
        String beginDate = rechargeQuery.getBeginDate();
        String endDate = rechargeQuery.getEndDate();

        Integer currentPage = rechargeQuery.getCurrentPage();
        Integer pageSize = rechargeQuery.getPageSize();

        QueryWrapper<Recharge> wrapper = new QueryWrapper<>();

        Page<Recharge> pageParam = new Page<>(currentPage, pageSize);


        // 用户id
        if (!StringUtils.isEmpty(userId)) {
            wrapper.eq("user_id", userId);
        }
        // 审核状态
        if (!StringUtils.isEmpty(state)) {
            wrapper.eq("state", state);
        }

        // 时间范围
        if (!StringUtils.isEmpty(beginDate)) {
            wrapper.gt("recharge_time", beginDate);
        }
        if (!StringUtils.isEmpty(endDate)) {
            wrapper.lt("recharge_time", endDate);
        }


        IPage<Recharge> rechargeIPage = baseMapper.selectPage(pageParam, wrapper);
        return rechargeIPage;
    }


    // TODO yb专区结束
}
