package com.xkkj.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xkkj.code.pojo.Repayment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xkkj.code.query.RepaymentQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xkkj
 * @since 2020-02-11
 */
public interface RepaymentService extends IService<Repayment> {
    //分页查询前台用户的还款信息
    IPage<Repayment> getQuery(RepaymentQuery repaymentQuery);
    //查询标的的还款列表信息
    List<Repayment> getByBorrowId(String borrowId);
    //还款操作
    void getRepay(String id,String borrowUserId);
    //等额本息还款金额计算
    Map<String,Integer> getrepaymentType1(int repaymentMonth, int bidInterest, int period, int bidAamount);
    //先息后本还款金额计算
    Map<String,Integer> getrepaymentType2(int repaymentMonth, int bidInterest, int period, int bidAamount);

}
