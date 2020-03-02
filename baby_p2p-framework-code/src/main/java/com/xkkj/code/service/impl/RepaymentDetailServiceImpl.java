package com.xkkj.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkkj.code.pojo.RepaymentDetail;
import com.xkkj.code.mapper.RepaymentDetailMapper;
import com.xkkj.code.query.RepaymentQuery;
import com.xkkj.code.service.RepaymentDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RepaymentDetailServiceImpl extends ServiceImpl<RepaymentDetailMapper, RepaymentDetail> implements RepaymentDetailService {
    @Autowired
    RepaymentDetailMapper repaymentDetailMapper;

    /**
     * 分页查询收款明细
     * @param repaymentQuery
     * @return
     */
    @Override
    public IPage<RepaymentDetail> getQuery(RepaymentQuery repaymentQuery) {
        Page<RepaymentDetail> repaymentDetailPage = new Page<>(repaymentQuery.getCurrentPage(),repaymentQuery.getPageSize());
        QueryWrapper<RepaymentDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("repayment_time");
        //判断时间
        if (StringUtils.isNotBlank(repaymentQuery.getBeginDate())){
            queryWrapper.gt("repayment_time",repaymentQuery.getBeginDate());
        }
        if (StringUtils.isNotBlank(repaymentQuery.getEndDate())){
            queryWrapper.lt("repayment_time",repaymentQuery.getEndDate());
        }
        //判断用户
        if (StringUtils.isNotEmpty(repaymentQuery.getUserId())){
            queryWrapper.eq("borrow_user_id",repaymentQuery.getUserId());
        }
        //判断还款方式
        if (repaymentQuery.getState()!=null){
            queryWrapper.eq("repayment_type",repaymentQuery.getState());
        }
        IPage<RepaymentDetail> repaymentDetailIPages = repaymentDetailMapper.selectPage(repaymentDetailPage, queryWrapper);
        return repaymentDetailIPages;
    }
}
